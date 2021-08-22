package cps;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class main_frame {

    public static void main_frame(){
        Properties pro= new Properties();

        try {
            pro.load(new FileInputStream("src/cps/log.properties"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "파일 불러오기 오류 -> " + e);
        }

        // 프레임 생성
        JFrame frame_name = new JFrame("cps");
        // 프레임 크기 설정
        frame_name.setSize(350, 300);

        // 프레임을 화면 가운데에 배치
        frame_name.setLocationRelativeTo(null);

        // 프레임을 닫았을 때 메모리에서 제거되도록 설정
        frame_name.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 레이아웃 설정
        frame_name.getContentPane().setLayout(null);

        JButton btn1 = new JButton("cps측정");
        JButton btn2 = new JButton("최고 cps");

        // ★ 버튼 위치와 크기 설정
        btn1.setBounds(30, 170, 122, 30);
        btn2.setBounds(182, 170, 122, 30);

        // ★ 프레임에다가 버튼 추가
        frame_name.getContentPane().add(btn1);
        frame_name.getContentPane().add(btn2);

        btn1.addActionListener(event -> {
            JOptionPane.showMessageDialog(null, "ok를 누를시 cps측정이 시작됩니다");
            cps_test.cps_test();
            frame_name.setVisible(false);
        });

        btn2.addActionListener(event -> {
            JOptionPane.showMessageDialog(null, "당신의 최고 cps는" + pro.getProperty("bast_cps") + "입니다.");
        });

        frame_name.setVisible(true);

        try {
            pro.save(new FileOutputStream("src/cps/log.properties"),"");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "파일 불러오기 오류 -> " + e);
        }
    }

}
