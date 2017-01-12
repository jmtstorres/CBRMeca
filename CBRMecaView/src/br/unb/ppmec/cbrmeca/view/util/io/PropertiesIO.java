package br.unb.ppmec.cbrmeca.view.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesIO {
	
	public static final String MODEL_USED = "model.used";
	public static final String MODEL_PATH = "model.path";
	
	public static void storeProps(Properties prop, File f){
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			prop.store(fos, "Configuracoes do App");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void loadProps(Properties prop, File f){
		FileInputStream input = null;
		try {
			input = new FileInputStream(f);
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
