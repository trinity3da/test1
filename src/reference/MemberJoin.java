package reference;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import reference.*;

public class MemberJoin extends JFrame {
	JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7; 
	JTextField tfName, tfYear, tfMonth, tfDay, tfPhone;
	JButton btnCheck, btnJoin, btnCancel;
	
	private Socket soc;
	private PrintWriter socketWriter;
	private BufferedReader socketReader;
	
	public MemberJoin() {
		formInfo();
		makingForm();
		start();
		
		try {
			soc = new Socket("192.168.0.112", 10109);
			socketWriter = new PrintWriter(soc.getOutputStream());
			socketReader = new BufferedReader(new InputStreamReader(
					soc.getInputStream()));
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "접속 실패");
		}
	}


	public void formInfo() {
		lbl1 = new JLabel("이         름 :   ");
		lbl2 = new JLabel("생년월일 : ");
		lbl3 = new JLabel("년");
		lbl4 = new JLabel("월");
		lbl5 = new JLabel("일");
		lbl6 = new JLabel("전화번호 : ");
		lbl7 = new JLabel("예) 1980 02 05처럼 4자리, 2자리, 2자리 숫자로 작성");
		tfName = new JTextField(10);
		tfYear = new JTextField(4);
		tfMonth = new JTextField(2);
		tfDay = new JTextField(2);
		tfPhone = new JTextField(10);
		btnCheck = new JButton("전화번호 중복확인");
		btnJoin = new JButton("가입");
		btnCancel = new JButton("취소");
	}
	class ImagePanel extends JPanel {
		Image image;

		public ImagePanel() {
			setLayout(null);
			image = new ImageIcon("img/coffee3.gif").getImage();
		}

		public void paint(Graphics g) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}
	}

	public void makingForm(){
		setTitle("회원가입");
		ImagePanel i = new ImagePanel();
		add(i);
		i.add(lbl1); // 이름
		lbl1.setBounds(15, 200, 70, 25); // 이름 위치
		i.add(tfName); // 이름필드
		tfName.setBounds(80, 200, 150, 25); // 이름 필드위치
		i.add(lbl2); // 생년월일 타이틀
		lbl2.setBounds(15, 270, 70, 25); // 타이틀 위치
		i.add(tfYear); // 생년필드
		tfYear.setBounds(80, 270, 40, 25); // 위치
		i.add(lbl3); // 년레이블
		lbl3.setBounds(125, 270, 70, 25); // 위치
		i.add(tfMonth); // 월필드
		tfMonth.setBounds(150, 270, 25, 25); // 위치
		i.add(lbl4); // 월레이블
		lbl4.setBounds(180, 270, 70, 25); // 위치
		i.add(tfDay); // 일필드
		tfDay.setBounds(200, 270, 25, 25); // 위치
		i.add(lbl5); // 일 레이블
		lbl5.setBounds(230, 270, 70, 25); // 위치
		i.add(lbl7);
		lbl7.setBounds(18, 295, 400, 25);
		i.add(lbl6); // 전화번호 레이블
		lbl6.setBounds(15, 350, 70, 25); // 위치
		i.add(tfPhone); // 전화번호 필드
		tfPhone.setBounds(90, 350, 100, 25); // 위치
		i.add(btnCheck); // 중복확인버튼
		btnCheck.setBounds(15, 420, 150, 30); // 위치
		i.add(btnJoin); // 가입버튼
		btnJoin.addActionListener(add);
		btnJoin.setBounds(15, 460, 70, 25); // 위치
		i.add(btnCancel);// 취소버튼
		btnCancel.setBounds(95, 460, 70, 25); // 위치
	}

	
	public void start(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 533);
		setResizable(false);
		setVisible(true);
	}
	
	public boolean validateInput() {

		if (tfName.getText().length() == 0) {
			JOptionPane.showMessageDialog(MemberJoin.this, "이름을 입력해주세요");
			return false;
		}else if (tfYear.getText().length() == 0) {
			JOptionPane.showMessageDialog(MemberJoin.this, "연도를 입력해주세요");
			return false;
		}else if (tfYear.getText().length() < 4 
				|| tfYear.getText().length() > 4) {
			JOptionPane.showMessageDialog(MemberJoin.this, "연도를 확인해주세요");
			return false;
		}else if (tfMonth.getText().length() == 0) {
			JOptionPane.showMessageDialog(MemberJoin.this, "월을 입력해주세요");
			return false;
		}else if (tfMonth.getText().length() < 2
				|| tfMonth.getText().length() > 2) {
			JOptionPane.showMessageDialog(MemberJoin.this, "월을 확인해주세요");
			return false;
		}else if (tfDay.getText().length() == 0) {
			JOptionPane.showMessageDialog(MemberJoin.this, "일을 입력해주세요");
			return false;
		}else if (tfDay.getText().length() < 2
				|| tfDay.getText().length() > 2) {
			JOptionPane.showMessageDialog(MemberJoin.this, "일을 확인해주세요");
			return false;
		}else if (tfPhone.getText().length() == 0) {
			JOptionPane.showMessageDialog(MemberJoin.this, "전화번호를 입력해주세요");
			return false;
		}else if (tfPhone.getText().length() < 10
				|| tfPhone.getText().length() > 11) {
			JOptionPane.showMessageDialog(MemberJoin.this, "전화번호를 확인해주세요");
			return false;
		}
		
		return true;
	}


	ActionListener add = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(!validateInput()) {
				return;
			}
			
			socketWriter.println("*Join*" + tfName.getText() + "*"+tfYear+tfMonth+tfDay+"*");
			socketWriter.flush();
			
			while (true) {
				String str;
				try {
					str = socketReader.readLine();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(MemberJoin.this, "IO 오류");
					return;
				}
				
				if (str == null)
					break;
				else if(str.equals("Join Success"))
					JOptionPane.showMessageDialog(MemberJoin.this, "가입 성공");
				else if(str.equals("Join Fail"))
					JOptionPane.showMessageDialog(MemberJoin.this, "가입 실패");
				
			}
		}
	};

	ActionListener check = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			socketWriter.println("*Check*" + tfName.getText() + "*"+tfYear+tfMonth+tfDay+"*");
			socketWriter.flush();
			
			while (true) {
				String str;
				try {
					str = socketReader.readLine();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(MemberJoin.this, "IO 오류");
					return;
				}
				
				if (str == null)
					break;
				else if(str.equals("Join Success"))
					JOptionPane.showMessageDialog(MemberJoin.this, "가입 성공");
				else if(str.equals("Join Fail"))
					JOptionPane.showMessageDialog(MemberJoin.this, "가입 실패");
			}
		}
	}; 


	public static void main(String[] args) throws Exception {

		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			System.out.println(info.getName());
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}

		new MemberJoin();
	}
}