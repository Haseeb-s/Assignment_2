
public class LinkedListDemo {
    public static void main(String args[]){
        FoodItemLinkedList <String>aList = new FoodItemLinkedList<String>();
        aList.insert("Debbie");
        aList.insert("Anthony");
        aList.insert("Yoseph");


        System.out.println(aList);

        System.out.println(aList.search("Debbie"));
        System.out.println(aList.search("Anthony"));
        System.out.println(aList.search("Yoseph"));
        System.out.println(aList.search("Any"));
        aList.delete("Debbie");
        System.out.println(aList.search("Debbie"));

        FoodItemLinkedList <UserInfo> uList = new FoodItemLinkedList <UserInfo>();
        uList.insert(new UserInfo("Debbie", "Password123"));
        uList.insert(new UserInfo("Anthony", "Password234"));
        uList.insert(new UserInfo("Yoseph", "Password345"));
        System.out.println(uList);

        UserInfo temp = new UserInfo("TEST","test");
        UserInfo temp1 = new UserInfo("Debbie", "Password123");
        System.out.println(uList.search(temp));
        System.out.println(uList.search(temp1));
    }
}
