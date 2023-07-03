package com.Ari_bilgi.Halper;

import javax.swing.*;
import java.awt.*;

public class Halper {
    public static final String PROJECT_TITLE ="Bitirme Projesi";
    public static final String DB_URL= "jdbc:mysql://localhost:3306/okul";
    public static final String DB_USERNAME="root";
    public static final String DB_PASSWORD="12345";

    public static  int screenCenterPoint(String eksen, Dimension size){
        int point;

        switch (eksen){
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width)/2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height- size.height)/2;
                break;
            default:
                point = 0 ;
        }
        return point;
    }

    public static void setLayout() {
        for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
            if ("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static boolean isFieldEmpty(JTextField field ){
        return field.getText().trim().isEmpty();
    }
    public static void showMsg(String str){
       String msg;
       String title;
       optionPaneTR();
       switch (str){
           case "fill":
               msg = "Lütfen tüm alanları doldurun!";
               title= "Hata!";
               break;
           case "done":
               msg="İşlem başarılı";
               title= "Başarılı";
               break;
           case "error":
               msg="Bir hata oluştu!";
               title= "Hata!";
               break;
           default:
               msg=str;
               title = "Mesaj";
       }
       JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str){
        String msg;
        optionPaneTR();
        switch (str){
            case "sure":
                msg = "Bu veriyi SİLMEK istediğinize emin misiniz?";
                break;
            default:
                msg=str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Son kararın mı?", JOptionPane.YES_NO_OPTION)==0;
    }

    public static void optionPaneTR(){
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
    }
}
