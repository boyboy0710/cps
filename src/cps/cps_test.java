package cps;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import static cps.Main.cps;

public class cps_test {



    public static void cps_test() {


        Properties pro= new Properties();

        try {
            pro.load(new FileInputStream("src/cps/log.properties"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "파일 불러오기 오류 -> " + e);
        }

        // 프레임 생성
        JFrame frame_name = new JFrame("cps측정기");
        // 프레임 크기 설정
        frame_name.setSize(350, 300);

        // 프레임을 화면 가운데에 배치
        frame_name.setLocationRelativeTo(null);

        // 프레임을 닫았을 때 메모리에서 제거되도록 설정
        frame_name.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 레이아웃 설정
        frame_name.getContentPane().setLayout(null);

        JButton btn1 = new JButton("클릭하세요!");

        // ★ 버튼 위치와 크기 설정
        btn1.setBounds(0, 0, 350, 300);

        // ★ 프레임에다가 버튼 추가
        frame_name.getContentPane().add(btn1);

        btn1.addActionListener(event -> {
            cps++;
            System.out.println("현재 클릭 횟수:" + cps);
        });


        frame_name.setVisible(true);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null,"당신의 cps는" + cps/10);
                cps = 0;
                if (cps/10 > Integer.parseInt(pro.getProperty("bast_cps"))) {
                    pro.setProperty("bast_cps",String.valueOf(cps/10));

                }
                main_frame.main_frame();

            }
        };
        timer.schedule(timerTask,10000);

        try {
            pro.save(new FileOutputStream("src/cps/log.properties"),"");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "파일 불러오기 오류 -> " + e);
        }

    }

}
