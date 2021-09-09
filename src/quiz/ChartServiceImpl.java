package quiz;
//차트 표현
import java.io.IOException;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ChartServiceImpl{
	private Parent root;
	private PieChart pieChart;
	private BarChart barChart;
	private AreaChart areaChart;
	private Label labelMan;
	private ArrayList<Label> labelAgeList;
	private ArrayList<Label> labelGenderList;
	private ArrayList<Label> labelTravelList;
	private Label labelSum;
	private int count;
	//이거로 기본화면 띄워주는 것
	public ChartServiceImpl() {  //생성자로 만들었다
		
		FXMLLoader loader =
new FXMLLoader(getClass().getResource("viewchart.fxml"));   //초기화화면 띄워주는 역할. 그러면서 기본띄어준다
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pieChart = (PieChart)root.lookup("#pieChart");
		barChart = (BarChart)root.lookup("#barChart");
		areaChart = (AreaChart)root.lookup("#areaChart");
		
		labelGenderList = new ArrayList<>();
		for(int i=0;i<2;i++) {  //i<2에서 2보단 size로 표현하는게 더 낫다
			labelGenderList.add((Label)root.lookup("#labelGender"+i));
		}
		labelAgeList=new ArrayList<>();
		for(int i=0;i<4;i++) {
			labelAgeList.add((Label)root.lookup("#labelAge"+i));
		}
		labelTravelList=new ArrayList<>();
		for(int i=0;i<4;i++) {
			labelTravelList.add((Label)root.lookup("#labelTravel"+i));
		}
	}
	
	public void viewChart(ArrayList<Inquiry> lists) {
		int ageList[] = new int[] {0,0,0,0};  //0 : 10,20,30,40대
		int genderList[] = new int[] {0,0};   //남,여
		int travelList[] = new int[] {0,0,0};
		
		for(int i=0;i<lists.size();i++) {
			count++;
			//age : 0(10) 1(20) 2(30) 3(40대)
			ageList[lists.get(i).getAge()]++;  //리스트의 get(i)을 getAge를 1씩 증가시켜라
			genderList[lists.get(i).getGender()]++;
			travelList[lists.get(i).getTravel()]++;
		}
		
		for(int i=0;i<2;i++) {
			labelGenderList.get(i).setText(genderList[i]+" 명");
		}
		
		for(int i=0;i<4;i++) {
			labelAgeList.get(i).setText(ageList[i]+" 명");
		}
		
		for(int i=0;i<3;i++) {
			labelTravelList.get(i).setText(travelList[i]+" 명");
		}
		
		labelSum = (Label)root.lookup("#labelSum");
		labelSum.setText(count+"");
		
		pieChart.setData(FXCollections.observableArrayList(
				new PieChart.Data("10대", ageList[0]),		
				new PieChart.Data("20대", ageList[1]),
				new PieChart.Data("30대", ageList[2]),
				new PieChart.Data("40대", ageList[3])
				));

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("남자");       
		series1.setData(FXCollections.observableArrayList(new XYChart.Data("성별 설문조사 비율", genderList[1])));  
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("여자");       
		series2.setData(FXCollections.observableArrayList(new XYChart.Data("성별 설문조사 비율", genderList[0])));
		barChart.getData().addAll(series1,series2);

		XYChart.Series series3 = new XYChart.Series();
		series3.setName("가고싶은 여행지");       
		series3.setData(FXCollections.observableArrayList(
				new XYChart.Data("해외 여행", travelList[0]),
				new XYChart.Data("국내 여행", travelList[1]),
				new XYChart.Data("가고싶은 곳 없음", travelList[2])
				));
		areaChart.getData().add(series3);

		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setTitle("설문조사 결과");
		stage.setScene(scene);
		stage.show();
	}
}
