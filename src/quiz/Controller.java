package quiz;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Controller implements Initializable{
	private Parent root;
	private ResultServiceImpl rs; 
	private Inquiry into;
	private DatabaseServiceImpl db;
	private ChartServiceImpl cs;
	public void setRoot(Parent root) {
		this.root = root; 
	}
	
	//결과보기 버튼
	public void resultBtn() {
		System.out.println("결과 보기");
		ArrayList<Inquiry> lists = db.selectSQL();  //데이터베이스부터 모든 정보를 가지고와야한다.그래서 list를 통해서 db에 저장된 모든정보가져오기
		cs = new ChartServiceImpl();  //여기서 먼저 생성자가 실행이되고 ChartServiceImpl로 넘어간다
		cs.viewChart(lists);  //차트쪽으로 모든정보 넘겨주고
		/*
		for(Inquiry i : lists) {
			System.out.println(i.getAge()+":"+i.getGender()+":"+i.getTravel());
		}
		*/
		/*
		for(int i =0; i<lists.size();i++) {
			System.out.println(lists.get(i).getAge()+ ":"+lists.get(i).getGender()+":" +lists.get(i).getTravel());
		}
		*/
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rs = new ResultServiceImpl() ;   //결과보기
		db = new DatabaseServiceImpl();  //가장 먼저 실행될 부분으로 데이터 베이스 연결하는 부분
	}
	
	//사용자가 입력한 데이터값을 가지고와서 저장하는 버튼
	public void saveBtn() {//확인 버튼
		System.out.println("저장");
		into = rs.result(root);//선택값 얻어오기. 리저트 서비스impl에서 결과값이 들어오고
		db.insertSQL(into);//그리고 들어온 결과값을 데이터 디비에  저장해라.그다음 DatabaseServiceImpl로 넘어간다
	}
	
	//취소버튼
	public void cancelBtn() {
		System.out.println("취소");	
	}
}
