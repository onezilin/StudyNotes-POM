package com.studynotes.demo05_eventListener;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Description: 自定义 MethodExecutionEventPublisher 实现发布功能
 */
public class Demo02_EventListenerTest {

    @Test
    public void test() {
        MethodExecutionEventPublisher eventPublisher = new MethodExecutionEventPublisher();
        eventPublisher.addMethodExecutionEventListener(new SimpleMethodExecutionEventListener());

        // 发布事件
        eventPublisher.methodToMonitor();
    }

    public enum MethodExecutionStatus {
        BEGIN, END;
    }

    /**
     * Description: 自定义事件监听器，监听 EventPublisher 发布的 EventObject 事件
     */
    public interface MethodExecutionEventListener extends EventListener {

        void onMethodBegin(Demo01_MyApplicationEvent event);

        void onMethodEnd(Demo01_MyApplicationEvent event);
    }

    public static class SimpleMethodExecutionEventListener implements MethodExecutionEventListener {
        @Override
        public void onMethodBegin(Demo01_MyApplicationEvent event) {
            String methodName = event.getMethodName();
            System.out.println("strat to execute the method[" + methodName + "].");
        }

        @Override
        public void onMethodEnd(Demo01_MyApplicationEvent event) {
            String methodName = event.getMethodName();
            System.out.println("finished to execute the method[" + methodName + "].");
        }
    }

    /**
     * Description: 事件发布器，负责维护 EventListener，发布 EventObject 事件
     */
    public static class MethodExecutionEventPublisher {
        private final List<MethodExecutionEventListener> listeners = new ArrayList<>();

        public void methodToMonitor() {
            Demo01_MyApplicationEvent event2Publish = new Demo01_MyApplicationEvent(this, "methodToMonitor");
            publishEvent(MethodExecutionStatus.BEGIN, event2Publish);
        }

        protected void publishEvent(MethodExecutionStatus status, Demo01_MyApplicationEvent event) {
            List<MethodExecutionEventListener> copyListeners = new ArrayList<>(listeners);
            for (MethodExecutionEventListener listener : copyListeners) {
                if (MethodExecutionStatus.BEGIN.equals(status)) listener.onMethodBegin(event);
                else listener.onMethodEnd(event);
            }
        }

        public void addMethodExecutionEventListener(MethodExecutionEventListener listener) {
            this.listeners.add(listener);
        }

        public void removeMethodExecutionEventListener(MethodExecutionEventListener listener) {
            this.listeners.remove(listener);
        }

        public void removeAllListeners() {
            this.listeners.clear();
        }
    }
}
