module merge {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires com.jfoenix;
	opens application to javafx.graphics, javafx.fxml;
}
