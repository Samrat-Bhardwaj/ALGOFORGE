class Bank {
    String manager;
    int rateOfInterest(){
        return 5;
    }
}
class SBI extends Bank {
    int rateOfInterest(){
        return 7;
    }
}
class ICICI extends Bank {
    int rateOfInterest(){
        return 9;
    }
}
class HDFC extends Bank {
    int prop;
    int rateOfInterest(){
        return 8;
    }
}


class Example {
    public static void main(String[] args){
        Bank bObj;

        bObj = new HDFC(); // upcasting, runtime

        HDFC hf = (HDFC)bObj; // downcasting
        
        System.out.println(bObj.rateOfInterest());
        // System.out.println(bObj.prop); // compile time error
    }
}