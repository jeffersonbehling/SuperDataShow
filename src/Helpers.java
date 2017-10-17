import java.awt.Component;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sun.javafx.webkit.ThemeClientImpl;

public class Helpers {

	public void clearTable(DefaultTableModel model) {
		for (int i = 0; i < model.getRowCount(); i++) {
			model.removeRow(i);
		}
		for (int i = 0; i < model.getRowCount(); i++) {
			model.removeRow(i);
		}
	}

	public void initMysql() {
		try {
			Thread t = new Thread(){
				public void run(){
					try {
						Runtime.getRuntime().exec("cmd.exe /C start /b C:\\xampp\\mysql_start.bat");
						
					} catch (IOException e) {
						System.out.println("Error: "+ e.getMessage());
					}
				}
			};
			t.start();
			
		} catch (Exception e) {
			System.out.println("Erro:" +e.getMessage());
		}
	}
	
	public void test() {
		try {
			Thread t = new Thread(){
				public void run(){
					
				}
			};
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t.start();
			
		} catch (Exception e) {
			System.out.println("Erro:" +e.getMessage());
		}
	}
	
}
