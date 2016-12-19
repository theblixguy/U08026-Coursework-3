package javafxapplication2;
import java.time.DayOfWeek;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author suyashsrijan
 */
public class ViewController extends Application implements IView {
    
    GridPane gridPane;
    TextField outputView;
    ArrayList<Button> buttonList;
    Text dayNamesText;
    private static final String ERROR_TEXT = "Error!";
    private static final String OUTPUT_VIEW_STYLE = "-fx-background-color: yellow; -fx-font-size: 18pt;";
    private CalculatorController controller;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new CalculatorController(this);
        primaryStage.setTitle("DateTime Calculator");
        primaryStage.setHeight(265);
        primaryStage.setWidth(785);
        primaryStage.setResizable(false);
        gridPane = new GridPane();
        outputView = new TextField();
        buttonList = new ArrayList<>();
        setupGUI();
        Scene scene = new Scene(gridPane, 800, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    @Override
    public void setupGUI() {
        dayNamesText = new Text("sun MON tue wed thu fri sat");
        
        for (int i = 0; i < 23; i++) {
            Button button = new Button();
            buttonList.add(button);
        }
        
        buttonList.get(0).setText("TM");
        buttonList.get(1).setText("DT");
        buttonList.get(2).setText("C");
        buttonList.get(3).setText("AC");
        buttonList.get(4).setText("7");
        buttonList.get(5).setText("4");
        buttonList.get(6).setText("1");
        buttonList.get(7).setText("0");
        buttonList.get(8).setText("8");
        buttonList.get(9).setText("5");
        buttonList.get(10).setText("2");
        buttonList.get(11).setText(".");
        buttonList.get(12).setText("9");
        buttonList.get(13).setText("6");
        buttonList.get(14).setText("3");
        buttonList.get(15).setText("=");
        buttonList.get(16).setText("*");
        buttonList.get(17).setText("/");
        buttonList.get(18).setText("-");
        buttonList.get(19).setText("+");
        buttonList.get(20).setText("TIME");
        buttonList.get(21).setText("ST");
        buttonList.get(22).setText("MD");
        
        buttonList.stream().map((b) -> {
            b.setPrefSize(60, 35);
            return b;
        }).forEachOrdered((b) -> {
            b.setOnAction((event) -> {
                controller.handleButtonPress(b.getText());
            });
        });
        
        dayNamesText.setFont(Font.font(null, FontWeight.BOLD, 25));
        outputView.setPrefSize(350, 140);
        outputView.setStyle(OUTPUT_VIEW_STYLE);
        outputView.setEditable(false);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        
        gridPane.add(outputView, 0, 0, 5, 4);
        gridPane.add(buttonList.get(0), 6, 0, 1, 1);
        gridPane.add(buttonList.get(1), 6, 1, 1, 1);
        gridPane.add(buttonList.get(2), 6, 2, 1, 1);
        gridPane.add(buttonList.get(3), 6, 3, 1, 1);
        gridPane.add(buttonList.get(4), 7, 0, 1, 1);
        gridPane.add(buttonList.get(5), 7, 1, 1, 1);
        gridPane.add(buttonList.get(6), 7, 2, 1, 1);
        gridPane.add(buttonList.get(7), 7, 3, 1, 1);
        gridPane.add(buttonList.get(8), 8, 0, 1, 1);
        gridPane.add(buttonList.get(9), 8, 1, 1, 1);
        gridPane.add(buttonList.get(10), 8, 2, 1, 1);
        gridPane.add(buttonList.get(11), 8, 3, 1, 1);
        gridPane.add(buttonList.get(12), 9, 0, 1, 1);
        gridPane.add(buttonList.get(13), 9, 1, 1, 1);
        gridPane.add(buttonList.get(14), 9, 2, 1, 1);
        gridPane.add(buttonList.get(15), 9, 3, 1, 1);
        gridPane.add(buttonList.get(16), 10, 0, 1, 1);
        gridPane.add(buttonList.get(17), 10, 1, 1, 1);
        gridPane.add(buttonList.get(18), 10, 2, 1, 1);
        gridPane.add(buttonList.get(19), 10, 3, 1, 1);
        gridPane.add(dayNamesText, 0, 4, 5, 1);
        gridPane.add(buttonList.get(20), 0, 5, 1, 1);
        gridPane.add(buttonList.get(21), 1, 5, 1, 1);
        gridPane.add(buttonList.get(22), 2, 5, 1, 1);
        
        buttonList.clear();
    }
    
    @Override
    public void clearScreen() {
        outputView.setText("");
    }
    
    @Override
    public void writeToOutput(String str) {
        outputView.setText(outputView.getText() + str);
    }
    
    @Override
    public void writeErrorToOutput() {
        outputView.setText(ERROR_TEXT);
    }
    
    @Override
    public void updateOutputWithBuffer(String buf) {
        outputView.setText(buf);
    }
    
    
    @Override
    public void updateCurrentDay(DayOfWeek day) {
        switch (day) {
            case MONDAY:
                dayNamesText.setText("sun MON tue wed thu fri sat");
                break;
            case TUESDAY:
                dayNamesText.setText("sun mon TUE wed thu fri sat");
                break;
            case WEDNESDAY:
                dayNamesText.setText("sun mon tue WED thu fri sat");
                break;
            case THURSDAY:
                dayNamesText.setText("sun mon tue wed THU fri sat");
                break;
            case FRIDAY:
                dayNamesText.setText("sun mon tue wed thu FRI sat");
                break;
            case SATURDAY:
                dayNamesText.setText("sun mon tue wed thu fri SAT");
                break;
            case SUNDAY:
                dayNamesText.setText("SUN mon tue wed thu fri sat");
                break;
        }
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
