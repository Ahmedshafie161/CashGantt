module com.example.mycashgantt {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires org.apache.poi.poi;


    opens com.example.mycashgantt to javafx.fxml;
    exports com.example.mycashgantt;
}