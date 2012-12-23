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
			JOptionPane.showMessageDialog(this, "���� ����");
		}
	}

	public void formInfo() {
		lbl1 = new JLabel("��         �� :   ");
		lbl2 = new JLabel("�������� : ");
		lbl3 = new JLabel("��");
		lbl4 = new JLabel("��");
		lbl5 = new JLabel("��");
		lbl6 = new JLabel("��ȭ��ȣ : ");
		lbl7 = new JLabel("��) 1980 02 05ó�� 4�ڸ�, 2�ڸ�, 2�ڸ� ���ڷ� �ۼ�");
		tfName = new JTextField(10);
		tfYear = new JTextField(4);
		tfMonth = new JTextField(2);
		tfDay = new JTextField(2);
		tfPhone = new JTextField(10);
		btnCheck = new JButton("��ȭ��ȣ �ߺ�Ȯ��");
		btnJoin = new JButton("����");
		btnCancel = new JButton("����");
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
		setTitle("ȸ������");
		ImagePanel i = new ImagePanel();
		add(i);
		i.add(lbl1); // �̸�
		lbl1.setBounds(15, 200, 70, 25); // �̸� ��ġ
		i.add(tfName); // �̸��ʵ�
		tfName.setBounds(80, 200, 150, 25); // �̸� �ʵ���ġ
		i.add(lbl2); // �������� Ÿ��Ʋ
		lbl2.setBounds(15, 270, 70, 25); // Ÿ��Ʋ ��ġ
		i.add(tfYear); // �����ʵ�
		tfYear.setBounds(80, 270, 40, 25); // ��ġ
		i.add(lbl3); // �ⷹ�̺�
		lbl3.setBounds(125, 270, 70, 25); // ��ġ
		i.add(tfMonth); // ���ʵ�
		tfMonth.setBounds(150, 270, 25, 25); // ��ġ
		i.add(lbl4); // �����̺�
		lbl4.setBounds(180, 270, 70, 25); // ��ġ
		i.add(tfDay); // ���ʵ�
		tfDay.setBounds(200, 270, 25, 25); // ��ġ
		i.add(lbl5); // �� ���̺�
		lbl5.setBounds(230, 270, 70, 25); // ��ġ
		i.add(lbl7);
		lbl7.setBounds(18, 295, 400, 25);
		i.add(lbl6); // ��ȭ��ȣ ���̺�
		lbl6.setBounds(15, 350, 70, 25); // ��ġ
		i.add(tfPhone); // ��ȭ��ȣ �ʵ�
		tfPhone.setBounds(90, 350, 100, 25); // ��ġ
		i.add(btnCheck); // �ߺ�Ȯ�ι�ư
		btnCheck.addActionListener(check);
		btnCheck.setBounds(15, 420, 150, 30); // ��ġ
		i.add(btnJoin); // ���Թ�ư
		btnJoin.addActionListener(add);
		btnJoin.setBounds(15, 460, 70, 25); // ��ġ
		i.add(btnCancel);// ���ҹ�ư
		btnCancel.addActionLIstener(cancel);
		btnCancel.setBounds(95, 460, 70, 25); // ��ġ
	}

	
	public void start(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 533);
		setResizable(false);
		setVisible(true);
	}
	
	public boolean validateInput() {

		if (tfName.getText().length() == 0) {
			JOptionPane.showMessageDialog(MemberJoin.this, "�̸��� �Է����ּ���");
			return false;
		}else if (tfYear.getText().length() == 0) {
			JOptionPane.showMessageDialog(MemberJoin.this, "������ �Է����ּ���");
			return false;
		}else if (tfYear.getText().length() < 4 
				|| tfYear.getText().length() > 4) {
			JOptionPane.showMessageDialog(MemberJoin.this, "������ Ȯ�����ּ���");
			return false;
		}else if (tfMonth.getText().length() == 0) {
			JOptionPane.showMessageDialog(MemberJoin.this, "���� �Է����ּ���");
			return false;
		}else if (tfMonth.getText().length() < 2
				|| tfMonth.getText().length() > 2) {
			JOptionPane.showMessageDialog(MemberJoin.this, "���� Ȯ�����ּ���");
			return false;
		}else if (tfDay.getText().length() == 0) {
			JOptionPane.showMessageDialog(MemberJoin.this, "���� �Է����ּ���");
			return false;
		}else if (tfDay.getText().length() < 2
				|| tfDay.getText().length() > 2) {
			JOptionPane.showMessageDialog(MemberJoin.this, "���� Ȯ�����ּ���");
			return false;
		}else if (tfPhone.getText().length() == 0) {
			JOptionPane.showMessageDialog(MemberJoin.this, "��ȭ��ȣ�� �Է����ּ���");
			return false;
		}else if (tfPhone.getText().length() < 10
				|| tfPhone.getText().length() > 11) {
			JOptionPane.showMessageDialog(MemberJoin.this, "��ȭ��ȣ�� Ȯ�����ּ���");
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
					JOptionPane.showMessageDialog(MemberJoin.this, "IO ����");
					return;
				}
				
				if (str == null)
					break;
				else if(str.equals("Join Success"))
					JOptionPane.showMessageDialog(MemberJoin.this, "���� ����");
				else if(str.equals("Join Fail"))
					JOptionPane.showMessageDialog(MemberJoin.this, "���� ����");
				
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
					JOptionPane.showMessageDialog(MemberJoin.this, "IO ����");
					return;
				}
				
				if (str == null)
					break;
				else if(str.equals("Join Success"))
					JOptionPane.showMessageDialog(MemberJoin.this, "���� ����");
				else if(str.equals("Join Fail"))
					JOptionPane.showMessageDialog(MemberJoin.this, "���� ����");
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
