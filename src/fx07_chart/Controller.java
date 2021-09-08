package fx07_chart;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class Controller implements Initializable{
	//차트
	PieChart pieChart;
	BarChart barChart;
	AreaChart areaChart;
	
	Parent root;
	public void setRoot(Parent root) {
		this.root = root;
		pieChart = (PieChart)root.lookup("#pieChart");
		barChart = (BarChart)root.lookup("#barChart");
		areaChart = (AreaChart)root.lookup("#areaChart");
		chart();//호출
	}
	public void chart() {
		pieChart.setData(FXCollections.observableArrayList(
				new PieChart.Data("AWT", 10),
				new PieChart.Data("Swing", 30),
				new PieChart.Data("JavaFx", 30),
				new PieChart.Data("Java", 30)
				));//fx에서 쓰는 일반적인 arraylist다.()안에는 초기화 하고하는 값
		
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("남자");
		series1.setData(FXCollections.observableArrayList(
				new XYChart.Data("2016",70),
				new XYChart.Data("2017",40),
				new XYChart.Data("2018",50),
				new XYChart.Data("2019",90)
				));
		
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("여자");
		series2.setData(FXCollections.observableArrayList(
				new XYChart.Data("2016",30),  //숫자가 똑같으면 나란히 배치가된다
				new XYChart.Data("2017",80),
				new XYChart.Data("2018",100),
				new XYChart.Data("2019",50)
				));
				
		//이렇게 비교로 나타낼수 있다
		barChart.getData().add(series1);
		barChart.getData().add(series2);
		
		XYChart.Series series3 = new XYChart.Series();
		series3.setName("평균온도");
		series3.setData(FXCollections.observableArrayList(
				new XYChart.Data("2016",10),
				new XYChart.Data("2017",6),
				new XYChart.Data("2018",15),
				new XYChart.Data("2019",11)
				));
		//area차트에 추가하기
		areaChart.getData().add(series3);
	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	

}
