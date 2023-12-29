package com.studynotes.java16_concurrency;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Description: 传统的同步执行的程序
 */
public class Demo02_AppletAsync extends Applet {
    private int count = 0;
    private Button onOff = new Button("Toggle"), start = new Button("Start");
    private TextField t = new TextField(10);
    private boolean runFlag = true;

    public void init() {
        add(t);
        start.addActionListener(new StartL());
        add(start);
        onOff.addActionListener(new OnOffL());
        add(onOff);
    }

    public void go() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (runFlag)
                t.setText(Integer.toString(count++));
        }
    }

    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // 会陷入go()中，不会返回，导致监听事件一直被占用
            go();
        }
    }

    class OnOffL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            runFlag = !runFlag;
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Demo02_AppletAsync applet = new Demo02_AppletAsync();
        Frame aFrame = new Frame("Counter1");
        aFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        aFrame.add(applet, BorderLayout.CENTER);
        aFrame.setSize(300, 200);
        applet.init();
        applet.start();
        aFrame.setVisible(true);
    }
}
