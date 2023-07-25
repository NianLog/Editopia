package com.AppGUI;

import java.io.*;
import javax.swing.*;

public class Files {
	public void saveFile(JTextArea textArea) {
		System.out.println("Save File is OK");
		 JFileChooser fileChooser = new JFileChooser();
	        int choice = fileChooser.showSaveDialog(null); // 显示文件保存对话框

	        if (choice == JFileChooser.APPROVE_OPTION) {
	            File file = fileChooser.getSelectedFile(); // 获取选择的文件
	            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
	                String text = textArea.getText(); // 获取文本区域的内容
	                writer.write(text); // 写入文件
	                JOptionPane.showMessageDialog(null, "文件保存成功");
	            } catch (IOException e) {
	                e.printStackTrace();
	                JOptionPane.showMessageDialog(null, "文件保存失败");
	            }
	        }
	}
}
