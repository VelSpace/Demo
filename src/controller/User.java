package controller;

public class User implements Comparable<User>{
	
	private int id ;
	private String name;
	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public int compareTo(User o) {
		return o.id  - this.id;
	}
	
//	public static void main(String[] args) {
////		List<User> objList = new ArrayList<>();
////		objList.add(new User(1,"Mani"));
////		objList.add(new User(4, "Uday"));
////		objList.add(new User(3, "Rohit"));
////		objList.add(new User(2, "vijay"));
////		objList.add(new User(1,"Manikandan"));
////		objList.add(new User(5, "vikas"));
////		
////		Collections.sort(objList);
////		
////		for(User user: objList) {
////			System.out.println(user.id + " ==> "+user.name);
////		}
//		
//		System.out.println("hellooooo");
//		
//		JSONObject obj = new JSONObject("{\"one\":\"two\",\"one1\":\"two2\"}");
//		System.out.println(obj.length());
//		System.out.println(obj.get("one"));
//	}
//	
	
}
