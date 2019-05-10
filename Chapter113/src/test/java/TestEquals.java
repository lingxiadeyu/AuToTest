/*


 */
public class TestEquals {
    public static void main(String[] args) {
        //用equals、==去比较String变量
        //两个String变量内容相同
        String abc = "111";
        String cba = "111";
        //把abc的值赋值给def
        String def=abc;
        System.out.println("两个string变量内容相同，使用==比较结果为true:"+(abc == cba));
        System.out.println("两个string变量内容相同，使用equals比较结果为true:"+(abc.equals(cba)));
        System.out.println("把一个String变量赋值给另一个String变量，使用==比较结果为true:"+(abc == def));
        System.out.println("把一个String变量赋值给另一个String变量，使用equals比较结果为true:"+abc.equals(def));

        /*
        总结：创建String变量时，在内存中创建一个引用地址和一个值的地址，引用地址指向值的地址，
        两个变量值相同，值的地址相同，引用地址都指向值的地址，所以用==和equals比较两个相同值的变量结果都为true
         */


        //用equeals、==去比较int变量
        int ghi = 1;
        int jkl = 1;

        System.out.println("两个int变量内容相同，使用==比较结果为true:"+(ghi == jkl));
        //整型变量不能用equals来比较,编译器会报错
//        if(ghi.equals(jkl)){
//            System.out.println("");
//        }

        //用equals、==去比较两个相同内容的对象
        String mno = new String("123");
        String pqr = new String("123");
        String stx = mno; //stx等于mno的值
        String zyz = "123";
        System.out.println("两个对象的内容相同，内存地址不相同，使用==比较的是内存地址，结果为false:"+(mno == pqr));
        System.out.println("两个对象的内容相同，内存地址不相同，使用equals比较的是内容，结果为true:"+(mno.equals(pqr)));
        System.out.println("对象赋值给另一个对象，对象的内存地址和内容都相同，使用==比较执行结果为true:"+(stx == mno));
        System.out.println("对象赋值给另一个对象，对象的内存地址和内容都相同，使用equals比较执行结果为true:"+(stx.equals(mno)));
        System.out.println("创建一个变量，值等于对象的内容，使用==比较变量和对象,因为变量的引用地址和对象的内存地址不同结果为false:"+(zyz == mno));
        System.out.println("创建一个变量，值等于对象的内容，使用equals比较变量和对象,因为变量的值地址和对象的值地址相同结果为true:"+(zyz.equals(mno)));


        /*
        总结：使用==比较的是内存地址，使用equals比较的是内容，内容也有地址，相同的内容，内存会给开辟相同的地址
         */

        //自定义一个类，创建类的实例并给属性赋值，值相同
        Users users=new Users("qinzhenxia","28");
        Users users1=new Users("qinzhenxia","28");

        System.out.println("自定义类，创建不同的对象，内容相同，因内存地址不同使用==比较结果为false:"+(users == users1));
        System.out.println("自定义类，创建不同的对象，内容相同，因object类下的equals方法是用==比较的内存地址，所以使用equals比较结果为false:"+(users.equals(users1)));

        //自定义一个类，创建类的实例并给属性赋值，值相同,该类重写了equals方法
        UsersTwo usersTwo=new UsersTwo("qinzhenxia","28");
        UsersTwo users1Two=new UsersTwo("qinzhenxia","28");

        System.out.println("自定义类，创建不同的对象，内容相同，因内存地址不同使用==比较结果为false:"+(usersTwo == users1Two));
        System.out.println("自定义类，创建不同的对象，内容相同，重写object类下的equals方法，比较内容所以使用equals比较结果为true:"+(usersTwo.equals(users1Two)));

        /*
        总结：
                    Object类的equals方法的实现代码如下：
                 boolean equals(Object  o){
                 return  this==o;
                  }
          该方法是用 == 比较的，比较的是对象的内存地址而不是内容地址，
          所以如果自定义的类不重写object时，使用==和equals比较的结果是一样的，都会是false。
          String类为什么可以用equals比较对象的内容能够成功呢？因为String类也重写了object的equals方法！

         */
    }
}
