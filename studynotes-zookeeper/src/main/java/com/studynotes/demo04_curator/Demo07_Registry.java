package com.studynotes.demo04_curator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.*;
import org.apache.curator.x.discovery.details.InstanceSerializer;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.curator.x.discovery.strategies.RoundRobinStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Description: 通过 curator-x-discovery 包，自定义实现服务注册与发现
 */
public class Demo07_Registry {

    // 注册的根路径
    private static final String ROOT = "/myCurator";

    @SneakyThrows
    public static void main(String[] args) {
        Demo07_Registry registry = new Demo07_Registry();

        ServiceInfo serviceInfo1 = new ServiceInfo(ROOT, "192.168.190.132", 8004);
        registry.registryRemote(serviceInfo1);
        ServiceInfo serviceInfo2 = new ServiceInfo(ROOT, "192.168.190.133", 8005);
        registry.registryRemote(serviceInfo2);

        Thread.sleep(5000);

        List<ServiceInfo> serviceInfoList1 = registry.queryServiceCache();
        serviceInfoList1.forEach(System.out::println);

        List<ServiceInfo> serviceInfoList2 = registry.queryServiceDiscovery(ROOT);
        serviceInfoList2.forEach(System.out::println);

        List<ServiceInfo> serviceInfoList3 = registry.queryServiceProvider(new RoundRobinStrategy<>());
        serviceInfoList3.forEach(System.out::println);

        System.in.read();
    }

    // curator-x-discovery 扩展包的入口类
    private final ServiceDiscovery<ServiceInfo> serviceDiscovery;

    // ServiceCache 由于缓存 ServiceInstance
    private final ServiceCache<ServiceInfo> serviceCache;

    private static final CuratorFramework CLIENT = CuratorUtil.CLIENT;;

    public Demo07_Registry() throws Exception {
        // 阻塞直到连接上 Zookeeper
        CLIENT.blockUntilConnected();

        // 序列化器
        InstanceSerializer<ServiceInfo> serializer = new JsonInstanceSerializer<>(ServiceInfo.class);

        // 初始化并启动 ServiceDiscovery
        serviceDiscovery = ServiceDiscoveryBuilder
                .builder(ServiceInfo.class)
                .client(CLIENT)
                .basePath(ROOT)
                .watchInstances(true) // 监听 ServiceInstance 的变化
                .serializer(serializer)
                .build();
        serviceDiscovery.start();

        // 初始化并启动 ServiceCache，监听 ROOT 节点（没有时创建）的变化，用于更新 ServiceCache
        serviceCache = serviceDiscovery.serviceCacheBuilder().name(ROOT).build();
        serviceCache.start();
    }

    /**
     * Description: 服务注册
     *
     * @param serviceInfo serviceInfo
     */
    public void registryRemote(ServiceInfo serviceInfo) throws Exception {
        // 将 ServerInfo 对象转换成 ServiceInstance 对象
        ServiceInstance<ServiceInfo> serviceInstance = ServiceInstance.<ServiceInfo>builder()
                .name(serviceInfo.getName()) // name 就是 ROOT 节点下的子节点
                .id(UUID.randomUUID().toString()) // id 就是 name 节点下的子节点
                .address(serviceInfo.getHost())
                .port(serviceInfo.getPort())
                .payload(serviceInfo)
                .build();

        // 注册到 ZooKeeper 中
        serviceDiscovery.registerService(serviceInstance);
    }

    /**
     * Description: 通过 ServiceCache 获取全部的 ServiceInstance 对象
     *
     * @return java.util.List<com.studynotes.curator.demo02.Demo01_Registry.ServiceInfo>
     */
    public List<ServiceInfo> queryServiceCache() {
        // 查询 ServiceCache 获取全部的 ServiceInstance 对象
        List<ServiceInstance<ServiceInfo>> serviceInstanceList = serviceCache.getInstances();
        return serviceInstanceList.stream().map(ServiceInstance::getPayload).collect(Collectors.toList());
    }

    /**
     * Description: 通过 ServiceDiscovery 获取指定 name （也就是 name 节点下的子节点）的 ServiceInstance 对象
     *
     * @param name ServiceInstance 的 name 属性
     */
    public List<ServiceInfo> queryServiceDiscovery(String name) throws Exception {
        return serviceDiscovery.queryForInstances(name).stream().map(ServiceInstance::getPayload).collect(Collectors.toList());
    }

    /**
     * Description: 通过 ServiceProvider 获取 ServiceInstance 对象
     *
     * @param strategy 策略模式，通过不同的策略获取 ServiceInstance 对象
     */
    public List<ServiceInfo> queryServiceProvider(ProviderStrategy<ServiceInfo> strategy) throws Exception {
        // 初始化并启动 ServiceProvider，获取 ROOT 节点（没有时创建）下的子节点
        ServiceProvider<ServiceInfo> serviceProvider =
                serviceDiscovery.serviceProviderBuilder().providerStrategy(strategy).serviceName(ROOT).build();
        serviceProvider.start();

        int i = 100;
        List<ServiceInfo> serviceInfoList = new ArrayList<>();
        while (i-- > 0) {
            // 从 ServiceProvider 中获取 ServiceInstance 对象
            ServiceInstance<ServiceInfo> serviceInstance = serviceProvider.getInstance();
            if (serviceInstance != null) {
                System.out.println(serviceInstance.getPayload());
                serviceInfoList.add(serviceInstance.getPayload());
            }
        }
        return serviceInfoList;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class ServiceInfo {

        private String name;

        private String host;

        private int port;
    }
}
