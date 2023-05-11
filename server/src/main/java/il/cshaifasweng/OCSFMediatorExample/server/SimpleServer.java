package il.cshaifasweng.OCSFMediatorExample.server;

import com.mysql.cj.xdevapi.Client;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.entities.Gender.female;
import static il.cshaifasweng.OCSFMediatorExample.entities.Gender.male;
import  il.cshaifasweng.OCSFMediatorExample.entities.Data;
public class SimpleServer extends AbstractServer {


	private static Session session;

	private static List<Student> getAllStudents() throws Exception {

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Student> query = builder.createQuery(Student.class);
		query.from(Student.class);
		List<Student> data = session.createQuery(query).getResultList();
		return data;
	}



	private static void generateStudents() {
		Student student1 = new Student("Mohamad","Hijazi","123123123", "28/08/2002" ,male,90,100,95);
		session.save(student1);
		session.flush();
		Student student2 = new Student("Majd","Abass","123456789", "15/05/1995" ,male,93,98,85);
		session.save(student2);
		session.flush();
		Student student3 = new Student("Mustafa","Jabareen","654213978", "15/02/2005" ,male,75,92,89);
		session.save(student3);
		session.flush();
		Student student4 = new Student("Alex","David","555320747", "23/02/2008" ,female,75,86,91);
		session.save(student4);
		session.flush();
		Student student5 = new Student("Shir","Sana","621354155", "30/02/2007" ,female,84,82,91);
		session.save(student5);
		session.flush();
		Student student6 = new Student("Yam","Yakov","621354122", "06/09/2006" ,female,65,100,98);
		session.save(student6);
		session.flush();
		Student student7 = new Student("Youval","Malog","545412362", "01/10/2010" ,female,95,90,85);
		session.save(student7);
		session.flush();
		Student student8 = new Student("Noya","Noam","621444878", "11/05/2007" ,female,100,100,95);
		session.save(student8);
		session.flush();
		Student student9 = new Student("lilia","Ben","333655829", "14/02/2008" ,female,84,96,55);
		session.save(student9);
		session.flush();
		Student student10 = new Student("Michal","Feldman","332658442", "23/08/2009" ,female,90,95,100);
		session.save(student10);
		session.flush();
		Student student11 = new Student("Adam","Rayan","223163544", "19/03/2006" ,male,98,100,81);
		session.save(student11);
		session.flush();

	}

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		SimpleServer.session = session;
	}


	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		// Add ALL of your entities here. You can also try adding a whole package.
		configuration.addAnnotatedClass(Student.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	public SimpleServer(int port) {
		super(port);
		SessionFactory sessionFactory = getSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		generateStudents();
		session.getTransaction().commit();
	}
	private  void UpdateGrade(Student st,int newGrade,int Gradenum)
	{
		session.beginTransaction();
		switch (Gradenum)
		{
			case 0:
				st.setFirstGrade(newGrade);
				break;
			case 1:
				st.setSecondGrade(newGrade);
				break;
			case 2:
				st.setThirdGrade(newGrade);
		}
		session.update(st);
		session.getTransaction().commit();
	}


	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String msgString = msg.toString();
		if (msg.getClass().equals(MsgClass.class)) {
			MsgClass myMsg = (MsgClass) msg;
			String msgtext=myMsg.getMsg();
			try {
				if (msgtext.equals("#get all students")) {
					try {
						MsgClass myMSg = new MsgClass("all students",getAllStudents());
						client.sendToClient(myMSg);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				if(msgtext.equals("#update"))
				{
					try {
						Student st=(Student)(myMsg.getObj());
						UpdateGrade(session.get(Student.class,st.getId()),myMsg.getNewGrade(),myMsg.getGradeNum());
						MsgClass clientMsg=new MsgClass("#updated");
						client.sendToClient(clientMsg);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (msgString.startsWith("#close")) {
			session.close();
		}

	}



}
