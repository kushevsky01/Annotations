import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Class> array;

    Main(){
        array = new ArrayList<>();
        this.array.add(byte.class);
        this.array.add(short.class);
        this.array.add(int.class);
        this.array.add(long.class);
        this.array.add(float.class);
        this.array.add(double.class);
        this.array.add(char.class);
    }
    public static List<Class> getArray(){
        return array;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Main array = new Main();
        Foo foo = new Foo();
        doAll(foo, "->");

    }


    public static <T> boolean contains(final T[] array, final T v){
        for(final Class e : Main.getArray()){
            if (e == v || v!= null && v.equals(e)){
                return true;
            }
        }
        return false;
    }

    public static void doAll(Object ob, String s) throws IllegalAccessException, InstantiationException {
        Class cls1 = ob.getClass();

        Field[] fields = cls1.getDeclaredFields();
        for (Field field: fields){
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation: annotations){
                if(annotation instanceof Scan){
                    Scan an = (Scan) annotation;
                    if (!contains(new List[]{Main.getArray()}, field.getType())){
                        System.out.println(s+an.name()+ ": "+field.get(ob));
                        doAll(field.getType().newInstance(), "   " +s);
                    }else{
                        System.out.println(s+an.name()+ ": "+ field.get(ob) );
                    }
                }

            }
        }

    }

}

class Foo{
//    @Scan(name = "test1")
//    String test = "Миру мир";
//
//    @Scan(name = "test2")
//    String len = "Love";
//
//    @Scan(name = "test3")
//    int i = 3;

    @Scan(name = "test")
     Boo boo ;
}

class Boo{
    @Scan()
    String test3 = "World";

    @Scan(name = "test")
    Moo moo;

    @Scan(name = "test2")
    String len = "Love";

    @Scan(name = "test3")
    int i = 3;



}

class Moo{
    @Scan()
    String test3 = "World";

    @Scan(name = "test2")
    String len = "Love";

    @Scan(name = "test3")
    int i = 3;
}