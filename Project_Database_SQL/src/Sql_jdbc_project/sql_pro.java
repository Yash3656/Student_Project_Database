package Sql_jdbc_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class sql_pro {
	String url = "jdbc:mysql://localhost/Student_Relatinal_project_database";
	String user = "root";
	String password ="Yash@123";
	public void Complete() throws Exception
	{
		int x;
		do {
			System.out.println("1.Display list of all student \n 2.	Display list of all projects."
					+ " \n 3.Display the number of students who are working on project “P01”."
					+ "\n 4.Display number of students who participated in more than one project");
			System.out.println("Enter ur Choice: --");
			Scanner sc = new Scanner(System.in);
			int ch = sc.nextInt();
			switch(ch)
			{
			case 1:
				display_stud();
				break;
				
			case 2:
				display_proj();
				break;
				
			case 3:
				display_no1();
				break;
				
			case 4:
				display_no2();
				break;
				
			case 5:
				find_no();
				break;
				
			case 6:
				display_info1();
				break;
				
			case 7:
				display_info2();
				break;
				
			case 8:
				display_max_des();
				break;
				
			case 9:
				display_young_detail();
				break;
				
			case 10:
				display_info3();
				break;
				
			default:
				System.out.println("Enter Valid Choice !");
			
			}
			System.out.println("Do u want to Continue: -");
			x = sc.nextInt();
		}
		while(x==1);
	}

	private void display_info3() throws SQLException {
		//Display the info of the student who participated in the project where total no of the student should be exact three.
		String s1 = "select * from Student,StudentProject where Student.st_no = StudentProject.st_no group by StudentProject.prj_no having count(StudentProject.st_no)=3 ";
				//+ "st_no in(select prj_no from StudentProject group by prj_no having count(st_no)=3)\";
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement st1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs1 = st1.executeQuery(s1);
		int i=1;
		while(rs1.next())
		{
				System.out.println(rs1.getString(i) +"\t"+ rs1.getString(i+1) + "\t" +rs1.getString(i+2) + "\t" +rs1.getString(i+3));
		}
	}

	private void display_young_detail() throws SQLException {
		//Display details of the youngest student
		String s1 = "select * from Student where  timestampdiff(DAY,st_dob,now())/365 = (select min(timestampdiff(DAY,st_dob,now())/365) from student)";
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement st1 = conn.createStatement();
		ResultSet rs1 = st1.executeQuery(s1);
		int i=1;
		while(rs1.next())
		{
				System.out.println(rs1.getString(i) +"\t"+ rs1.getString(i+1) + "\t" +rs1.getString(i+2) + "\t" +rs1.getString(i+3));
		}
		
	}

	private void display_max_des() throws SQLException {
		//Display the student who played the max designation(e.g. manager,programmer) in the same project.
		String s1 = "select st_no,st_name,st_dob,st_doj from Student where st_no in(select st_no from StudentProject where designation='PROGRAMMER')";
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement st1 = conn.createStatement();
		ResultSet rs1 = st1.executeQuery(s1);
		int i=1;
		while(rs1.next())
		{
				System.out.println(rs1.getString(i) +"\t"+ rs1.getString(i+1) + "\t" +rs1.getString(i+2) + "\t" + rs1.getString(i+3));
		}
		
	}

	private void display_info2() throws SQLException {
		//Display the student information who is a programmer
		String s1 = "select st_no,st_name,st_dob,st_doj from Student where st_no in(select st_no from StudentProject where designation='PROGRAMMER')";
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement st1 = conn.createStatement();
		ResultSet rs1 = st1.executeQuery(s1);
		int i=1;
		while(rs1.next())
		{
				System.out.println(rs1.getString(i) +"\t"+ rs1.getString(i+1) + "\t" +rs1.getString(i+2) + "\t" + rs1.getString(i+3));
		}
		
	}

	private void display_info1() throws SQLException {
		//Display the information (no,name,age) of student  who made the project in java
		String s1 = "select st_no,st_name,timestampdiff(YEAR,st_dob,now()) from Student where st_no in(select st_no from StudentProject where prj_no='P02')";
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement st1 = conn.createStatement();
		ResultSet rs1 = st1.executeQuery(s1);
		int i=1;
		while(rs1.next())
		{
				System.out.println(rs1.getString(i) +"\t"+ rs1.getString(i+1) + "\t" +rs1.getString(i+2));
		}
	}

	private void find_no() throws SQLException {
		//Find number of students who did not participate in any project
		String s1 = "select count(distinct st_no) from Student  where st_no not in (select st_no from StudentProject)";
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement st1 = conn.createStatement();
		ResultSet rs1 = st1.executeQuery(s1);
		while(rs1.next())
		{
		System.out.println(rs1.getString(1));
		}
	}

	private void display_no2() throws SQLException {
		//Display number of students who participated in more than one project
		String s1 = "select st_no from StudentProject having count(prj_no)>1;";
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement st1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs1 = st1.executeQuery(s1);
		int count =0;
		while(rs1.next())
		{
			++count;
		}
		System.out.println(count);
		
	}

	private void display_no1() throws SQLException {
		//Display the number of students who are working on project “P01”
		String s = "select count(distinct st_no) from StudentProject where prj_no='P01'";
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(s);
		while(rs.next())
		{
		System.out.println(rs.getString(1));
		}
		
	}

	private void display_proj() throws Exception {
		//Display list of all projects
		String s = "select Project.prj_no,Project.prj_name from Project";
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(s);
		int i=1;
		while(rs.next())
		{
			if(i>0&&i<3)
			{
			System.out.println("Project No: --" + rs.getString(i) +"\t"+ "Project Name: --" + rs.getString(i+1));
			
			}
		}
		
	}

	private void display_stud() throws Exception {
		//Display list of all student 
		String s = "select Student.st_no,Student.st_name from Student";
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(s);
		int i=1;
		while(rs.next())
		{
			if(i>0&&i<3)
			{
			System.out.println("Student No: --" + rs.getString(i) +"\t"+ "Student Name: --" + rs.getString(i+1));
			
			}
		}
		
		
	}

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		sql_pro a1  =new sql_pro();
		a1.Complete();

	}

}
