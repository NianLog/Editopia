package com.AppGUI;
import java.awt.MenuItem;
import java.awt.event.*;
public class MenuEvent implements ActionListener{
	private MenuItem newfile;
	private MenuItem openfile;
	private MenuItem openfolder;
	private MenuItem timeopens;
	private MenuItem autoline;
	private MenuItem savedit;
	private MenuItem copyedit;
	private MenuItem saedit;
	private MenuItem tuiedit;
	private MenuItem jinedit;
	private MenuItem uioedit;
	private MenuItem sotext;
	private MenuItem titext;
//	private MenuItem ItemArr[]= {openFileItem,newFileItem,};
	public MenuEvent(MenuItem newfile, MenuItem openfile, MenuItem openfolder, MenuItem timeopens, MenuItem autoline, MenuItem savedit, MenuItem copyedit, MenuItem saedit, MenuItem tuiedit, MenuItem jinedit, MenuItem uioedit, MenuItem sotext, MenuItem titext) {
	    this.newfile = newfile;
	    this.openfile = openfile;
	    this.openfolder = openfolder;
	    this.timeopens = timeopens;
	    this.autoline = autoline;
	    this.savedit = savedit;
	    this.copyedit = copyedit;
	    this.saedit = saedit;
	    this.tuiedit = tuiedit;
	    this.jinedit = jinedit;
	    this.uioedit = uioedit;
	    this.sotext = sotext;
	    this.titext = titext;
	    //添加事件监听
	    newfile.addActionListener(this);
	    openfile.addActionListener(this);
	    openfolder.addActionListener(this);
	    timeopens.addActionListener(this);
	    autoline.addActionListener(this);
	    savedit.addActionListener(this);
	    copyedit.addActionListener(this);
	    saedit.addActionListener(this);
	    tuiedit.addActionListener(this);
	    jinedit.addActionListener(this);
	    uioedit.addActionListener(this);
	    sotext.addActionListener(this);
	    titext.addActionListener(this);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		MenuItem menuItem = (MenuItem) e.getSource();
		if (menuItem.equals(newfile)) {
		    // 在这里编写新文件的逻辑
		} else if (menuItem.equals(openfile)) {
		    // 在这里编写打开文件的逻辑功能
		} else if (menuItem.equals(openfolder)) {
		    // 在这里编写打开文件夹的逻辑功能
		} else if (menuItem.equals(timeopens)) {
		    // 在这里编写最近打开的文件记录功能
		} else if (menuItem.equals(autoline)) {
		    // 在这里编写自动换行的逻辑功能
		} else if (menuItem.equals(savedit)) {
		    // 在这里编写保存编辑的逻辑功能
		} else if (menuItem.equals(copyedit)) {
		    // 在这里编写复制选中文本的逻辑功能
		} else if (menuItem.equals(saedit)) {
		    // 在这里编写粘贴文本的逻辑功能
		} else if (menuItem.equals(tuiedit)) {
		    // 在这里编写撤销编辑的逻辑功能
		} else if (menuItem.equals(jinedit)) {
		    // 在这里编写前进编辑的逻辑功能
		} else if (menuItem.equals(uioedit)) {
		    // 在这里编写指定编码格式重新打开文件的逻辑功能
		} else if (menuItem.equals(sotext)) {
		    // 在这里编写搜索文本的逻辑功能
		} else if (menuItem.equals(titext)) {
		    // 在这里编写替换文本的逻辑功能
		}

	}
}
