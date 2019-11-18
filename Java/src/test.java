
public class test {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Person p1 = new Person("小明" ,20,'男' );
        Person p2 = new Person("小红" ,23,'女' );

         //创建Class对象的方式一：(对象.getClass())，获取person类中的字节码文件
         Class<?> class1 = p1.getClass();
        System. out.println(p1.getClass().getName());
         Class<?> class2 = p2.getClass();
        System. out.println(class1 == class2);

        System. out.println("==============================" );
         //创建Class对象的方式二：(类.class:需要输入一个明确的类，任意一个类型都有一个静态的class属性)
         Class<?> class3 = Person.class;
        System. out.println(class1 == class3);

        System. out.println("==============================" );
         //创建Class对象的方式三：(forName():传入时只需要以字符串的方式传入即可)
         //通过Class类的一个forName（String className)静态方法返回一个Class对象，className必须是全路径名称；
         //Class.forName()有异常：ClassNotFoundException

         Class<?> class4 = null;
         try {
            class4 = Class.forName("Person");
        } catch (ClassNotFoundException e) {
             // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System. out.println(class4 == class3);
        
        @SuppressWarnings("deprecation")
		Object o = class4.newInstance();
        System.out.println(o.toString());
	}
} 	

class Person implements China{
    private String name;
    private int age ;
    private char sex ;

    public Person() {
         super ();
   }

    public Person(String name, int age, char sex) {
         super ();
         this .name = name;
         this .age = age;
         this .sex = sex;
   }

    public String getName() {
         return name ;
   }

    public void setName(String name) {
         this .name = name;
   }

    public int getAge() {
         return age ;
   }

    public void setAge(int age) {
         this .age = age;
   }

    public char getSex() {
         return sex ;
   }

    public void setSex(char sex) {
         this .sex = sex;
   }
    public void eat()
   {
        System. out .println("吃了" );
   }

    @Override
    public String toString() {
         return "Person [name=" + name + ", age=" + age + ", sex=" + sex + "]" ;
   }

    @Override
    public void sayChina() {
         // TODO Auto-generated method stub
        System. out .println("作者：" + AUTHOR + "国籍："+ NATIONAL );
   }

    @Override
    public String sayHello(String name, int age, char sex) {
         // TODO Auto-generated method stub
         return "姓名:" + name + "年龄："+ age + "性别:" + sex;
   }
}