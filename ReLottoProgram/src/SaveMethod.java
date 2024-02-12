import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;

public class SaveMethod {
    private PurchasePage purchasePage;
    private File lottoNumFile;
    private static File amount;
    private static int fileCounter = 0;

    public SaveMethod(PurchasePage purchasePage) {
        this.purchasePage = purchasePage;
        generateNewFile();
    }

    private void generateNewFile() {
        String fileName = "lottoNums" + fileCounter + ".txt";
        lottoNumFile = new File(fileName);
    }

    public void saveLottoNums() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(lottoNumFile))) {
            writeLabels(writer, purchasePage.lbl1);
            writeLabels(writer, purchasePage.lbl2);
            writeLabels(writer, purchasePage.lbl3);
            writeLabels(writer, purchasePage.lbl4);
            writeLabels(writer, purchasePage.lbl5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // After saving, generate a new file for the next use
        fileCounter++;
        generateNewFile();
    }

    private void writeLabels(BufferedWriter writer, JLabel[] labels) throws IOException {
        for (JLabel label : labels) {
            writer.write(label.getText());
            writer.write(" ");
        }
        writer.newLine(); // Add a newline after writing all labels
    }
    public static void saveAmount() {
    	try(BufferedWriter writer = new BufferedWriter(new FileWriter(amount))){
    		User.getAmount();
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }
}