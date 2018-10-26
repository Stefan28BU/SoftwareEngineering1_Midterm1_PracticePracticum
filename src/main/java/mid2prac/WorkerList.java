package mid2prac;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "edu.baylor.ecs.mid2prac")
public class WorkerList {
	private List<Worker> workerList = new ArrayList<>();
	
	@XmlElement(name = "worker")
	public List<Worker> getWorkerList() {
		return workerList;
	}

	public void setWorkerList(List<Worker> workerList) {
		this.workerList = workerList;
	}
}
