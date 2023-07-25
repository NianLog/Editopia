package com.AppGUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

public class EditFrame extends Frame{
	//加载语言文件
	Locale locale=Locale.getDefault();
//	ResourceBundle bundle=ResourceBundle.getBundle("language", locale);
	ResourceBundle bundle = loadResourceBundle("language", locale);
	String fname=bundle.getString("deframe");
	public JTextArea textArea;
    private LineNumbers lineNum;
    
    int deTextSize=16;//默认文本大小（全局）
    String hexColor_he = "#808080"; // 16进制颜色代码（红色）
    Color color_he = Color.decode(hexColor_he); // 将16进制颜色代码转换为Color对象
    String hexColor_bg = "#C0C0C0"; // 16进制颜色代码（红色）
    Color color_bg = Color.decode(hexColor_bg); // 将16进制颜色代码转换为Color对象
    
	//创建菜单
	MenuBar mue=new MenuBar();
	Menu filemu=new Menu(bundle.getString("filemenu"));
	Menu editmu=new Menu(bundle.getString("editmenu"));
	Menu somu=new Menu(bundle.getString("somenu"));
	Menu toolmu=new Menu(bundle.getString("toolmenu"));
	Menu viewmu=new Menu(bundle.getString("viewmenu"));
	Menu confmu=new Menu(bundle.getString("confmenu"));
	Menu helpmu=new Menu(bundle.getString("helpmenu"));
	
	MenuItem newfile=new MenuItem(bundle.getString("newfile"));
	MenuItem openfile=new MenuItem(bundle.getString("openfile"));
	MenuItem openfolder=new MenuItem(bundle.getString("openfoloder"));
	MenuItem timeopens=new MenuItem(bundle.getString("timefile"));
	
	MenuItem autoline=new MenuItem(bundle.getString("autoeditline"));
	MenuItem savedit=new MenuItem(bundle.getString("saveedit"));
	MenuItem copyedit=new MenuItem(bundle.getString("copytext"));
	MenuItem saedit=new MenuItem(bundle.getString("satext"));
	MenuItem tuiedit=new MenuItem(bundle.getString("quashedit"));
	MenuItem jinedit=new MenuItem(bundle.getString("advanceedit"));
	MenuItem uioedit=new MenuItem(bundle.getString("Newencoding"));
	
	MenuItem sotext=new MenuItem(bundle.getString("findtext"));
	MenuItem titext=new MenuItem(bundle.getString("reptext"));
	
	Panel p1=new Panel();
	JTextPane tp1 = new JTextPane();
    JScrollPane scrollPane = new JScrollPane(tp1);
    
    LineNumbers lineNumbers = new LineNumbers();
    
	public EditFrame() {
		// 设置窗体标题
        setTitle("Editopia");
        into();
        
	}
	public EditFrame(String fname) {
		setTitle(fname+" - Editopia");
		into();
	}
	//通用初始化步骤
	private void into() {
		
        // 设置窗体大小
        setSize(900, 550);
        //布局设置
        setLayout(new BorderLayout());
        setGlobalTextStyle();
        // 为菜单项添加动作监听器
        MenuEvent menuEvent = new MenuEvent(newfile, openfile, openfolder, timeopens, autoline, savedit, copyedit, saedit, tuiedit, jinedit, uioedit, sotext, titext);
        //添加组件
        filemu.add(newfile);
        filemu.add(openfile);
        filemu.add(openfolder);
        filemu.add(timeopens);
        editmu.add(autoline);
        editmu.add(savedit);
        editmu.add(copyedit);
        editmu.add(saedit);
        editmu.add(tuiedit);
        editmu.add(jinedit);
        editmu.add(uioedit);
        somu.add(sotext);
        somu.add(titext); 
        mue.add(filemu);
        mue.add(editmu);
        mue.add(somu);
        mue.add(toolmu);
        mue.add(viewmu);
        mue.add(confmu);
        mue.add(helpmu);
        //设置中间的布局
        textArea = new JTextArea();
        textArea.setFont(new Font("微软雅黑", Font.PLAIN, deTextSize));
        //监听事件-快捷键Ctrl+s保存文件
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
                    // 执行保存文件的操作
                	Files file1=new Files();
                    file1.saveFile(textArea);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(textArea);
        lineNum = new LineNumbers();
        scrollPane.setRowHeaderView(lineNum);
        p1.setLayout(new BorderLayout());
        p1.add(scrollPane,BorderLayout.CENTER);
        textArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO 自动生成的方法存根
				lineNum.updateLineCount();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO 自动生成的方法存根
				lineNum.updateLineCount();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO 自动生成的方法存根
				lineNum.updateLineCount();
			}
        });
        
        this.add(p1);
        setMenuBar(mue);
        // 设置窗体关闭时的操作
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        //pack();
        // 设置窗体居中显示
        setLocationRelativeTo(null);
        //设置窗体可见
        setVisible(true);
	}
	/*
	//行数显示
	private class LineNumbers extends JComponent {
        private int lineCount;
        //更新行号
        public void updateLineCount() {
            int lines = textArea.getLineCount();
            if (lineCount != lines) {
                lineCount = lines;
                repaint();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.GRAY);
            g.setFont(new Font("微软雅黑", Font.PLAIN, deTextSize));

            FontMetrics fm = g.getFontMetrics();
            int lineHeight = fm.getHeight();

            Rectangle clip = g.getClipBounds();
            int startOffset = textArea.viewToModel(new Point(0, clip.y));
            int endOffset = textArea.viewToModel(new Point(0, clip.y + clip.height));

            try {
                int startLine = textArea.getLineOfOffset(startOffset);
                int endLine = textArea.getLineOfOffset(endOffset);

                for (int i = startLine; i <= endLine; i++) {
                    Rectangle rect = textArea.modelToView(textArea.getLineStartOffset(i));
                    int y = rect.y + rect.height;
                    int lineNumber = i + 1;

                    int stringWidth = fm.stringWidth(String.valueOf(lineNumber));
                    int x = (getWidth() - stringWidth) / 2;
                    g.drawString(String.valueOf(lineNumber), x, y);
                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(30, textArea.getHeight());
        }
    }
    */
	private class LineNumbers extends JComponent {
		private int lineCount;
		private int padding = 10;
		
		// 更新行号
		public void updateLineCount() {
			int lines = textArea.getLineCount();
			if (lineCount != lines) {
				lineCount = lines;
				revalidate(); // 更新组件的布局
				repaint();
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font("微软雅黑", Font.PLAIN, deTextSize));
			FontMetrics fm = g.getFontMetrics();
			int lineHeight = fm.getHeight();

			Rectangle clip = g.getClipBounds();
			int startOffset = textArea.viewToModel(new Point(0, clip.y));
			int endOffset = textArea.viewToModel(new Point(0, clip.y + clip.height));

			try {
				int startLine = textArea.getLineOfOffset(startOffset);
				int endLine = textArea.getLineOfOffset(endOffset);
				int currentLine = textArea.getLineOfOffset(textArea.getCaretPosition()); // 获取光标所在的行号

				for (int i = startLine; i <= endLine; i++) {
					Rectangle rect = textArea.modelToView(textArea.getLineStartOffset(i));
					int y = rect.y + rect.height;
					int lineNumber = i + 1;

					if (lineNumber == currentLine + 1) { // 判断是否为光标所在行号
						g.setColor(color_he); // 使用较深的背景色绘制光标所在行号
					} else {
						g.setColor(color_bg);
					}

					int stringWidth = fm.stringWidth(String.valueOf(lineNumber));
					int x = (getWidth() - stringWidth) / 2;
					g.fillRect(0, y - lineHeight, getWidth(), lineHeight); // 绘制背景色
					g.setColor(Color.WHITE); // 设置文本颜色为白色
					g.drawString(String.valueOf(lineNumber), x, y); // 绘制行号文本
				}
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}

		@Override
		public Dimension getPreferredSize() {
			FontMetrics fm = getFontMetrics(new Font("微软雅黑", Font.PLAIN, deTextSize));
			int stringWidth = fm.stringWidth(String.valueOf(lineCount));
			int width = stringWidth + padding * 2; // 根据文本宽度和内边距计算组件的宽度
			int height = textArea.getHeight();
			return new Dimension(width, height);
		}
	}
	//中文等语言正确加载支持 By NianSir
	private static ResourceBundle loadResourceBundle(String baseName, Locale locale) {
		try {
			InputStream stream = Main.class.getResourceAsStream(baseName + ".properties");
			if (stream != null) {
				return new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResourceBundle.getBundle(baseName, locale, new UTF8Control());
	}

	private static class UTF8Control extends ResourceBundle.Control {
		@Override
		public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IOException, IllegalAccessException, InstantiationException {
			String bundleName = toBundleName(baseName, locale);
			String resourceName = toResourceName(bundleName, "properties");
			try (InputStream stream = loader.getResourceAsStream(resourceName)) {
				if (stream != null) {
					return new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
				}
			}
			return super.newBundle(baseName, locale, format, loader, reload);
		}
	}
	//文本属性设置
	private void setGlobalTextStyle() {
		StyleContext styleContext = new StyleContext();
		Style defaultStyle = styleContext.getStyle(StyleContext.DEFAULT_STYLE);

		StyleConstants.setFontFamily(defaultStyle, "宋体");
		StyleConstants.setFontSize(defaultStyle, 26);
		StyleConstants.setForeground(defaultStyle, Color.BLACK);

		StyledDocument document = tp1.getStyledDocument();
		document.setLogicalStyle(0, defaultStyle);
	}
}
