package quiz;

import java.util.ArrayList;

import javafx.scene.Parent;
import javafx.scene.control.*;

public class ResultServiceImpl{
	//해당하는 라디오버튼에 대한 값들을 전부 찾는다
	public Inquiry result(Parent root) {
		//여행지에 대한 값
		Inquiry into = new Inquiry();   //into : 여기에 추가하겠다
		ArrayList<RadioButton> radioTravel = new ArrayList<>();
		for(int i=0;i < 3;i++) {//버튼을 0,1,2로 표현
			radioTravel.add((RadioButton)root.lookup("#radioTr"+i));
			if(radioTravel.get(i).isSelected()) //라디오버튼 선택되있으면 into로 간다
				into.setTravel(i); 
		}
		
		//age에 대한 값으로 역시 0번부터 시작
		ArrayList<RadioButton> radioAge = new ArrayList<>();
		for(int i=0;i<4;i++) {
			radioAge.add((RadioButton)root.lookup("#radioAge"+i));
			if(radioAge.get(i).isSelected())
				into.setAge(i);   //나이도 저장하고
		}
		
		RadioButton man = (RadioButton)root.lookup("#radioGender1");
		if(man.isSelected())
			into.setGender(1);  //성별도 저장한다(남)
		else
			into.setGender(0);  //성별도 저장한다(여)
		return into;  //그럼 결과적으로 여기에 숫자들이 모두 들어오고, 이것을 데이터 베이스에 넣는다. 이 메소드를 호출했던 곳이 controller
	}
}
