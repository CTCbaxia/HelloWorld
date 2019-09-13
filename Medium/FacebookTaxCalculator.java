/*
Facebook
Calculate tax if Salary and Tax Brackets are given as list in the form
[ [10000, 0.3],[20000, 0.2], [30000, 0.1], [null, .1]]
null being rest of the salary
Big O for both questions

Time: O(nlogn)
Space: O(1)
*/
public class taxCalculator {
    public static void main(String[] args) {
        List<Tax> taxBracket = new ArrayList<>();
        taxBracket.add(new Tax(3000, 0.4));
        taxBracket.add(new Tax(null, 0.1));
        taxBracket.add(new Tax(1000, 0.2));
        taxBracket.add(new Tax(2000, 0.3));
        System.out.println(taxCalculator1(taxBracket, 4000));
        System.out.println(taxCalculator2(taxBracket, 4000));
        System.out.println(taxCalculator3(taxBracket, 4000));
    }
    //compute from small to large
    public static class Tax{
        Integer bar;
        double rate;
        public Tax(Integer _bar, double _rate){
            bar = _bar;
            rate = _rate;
        }
    }
    public static int taxCalculator1(List<Tax> taxBracket, int income){
        //sort taxBracket ascending order, small to large, null should be the first
        Collections.sort(taxBracket, new Comparator<Tax>(){
            public int compare(Tax t1, Tax t2){
                if(t1.bar == null) return -1;
                if(t2.bar == null) return 1;
                return t1.bar - t2.bar;
            }
        });
        
        //compute tax
        int res = 0;
        for(int i = 0; i < taxBracket.size(); i++){
            if(i < taxBracket.size() - 1 && taxBracket.get(i + 1).bar <= income){
                //the whole bracket
                int l = taxBracket.get(i).bar == null ? 0 : taxBracket.get(i).bar;
                int r = taxBracket.get(i + 1).bar;
                res += (r - l) * taxBracket.get(i).rate;
            }else{
                //part of bracket
                int l = taxBracket.get(i).bar == null ? 0 : taxBracket.get(i).bar;
                res += (income - l) * taxBracket.get(i).rate;
                break;//cannot compute more
            }
        }
        return res;
    }
    
    public static int taxCalculator2(List<Tax> taxBracket, int income){
        //sort taxBracket descending order, large to small, null should be the last
        Collections.sort(taxBracket, new Comparator<Tax>(){
            public int compare(Tax t1, Tax t2){
                if(t1.bar == null) return 1;
                if(t2.bar == null) return -1;
                return t2.bar - t1.bar;
            }
        });
        
        int res = 0;
        for(int i = 0; i < taxBracket.size(); i++){
            if(i < taxBracket.size() - 1 && income < taxBracket.get(i).bar){
                continue;//skip
            }else if(i > 0 && income >= taxBracket.get(i - 1).bar){
                int l = taxBracket.get(i - 1).bar;
                int r = taxBracket.get(i).bar == null ? 0 : taxBracket.get(i).bar;
                res += (l - r) * taxBracket.get(i).rate;
            }else{
                int l = taxBracket.get(i).bar == null ? 0 : taxBracket.get(i).bar;
                res += (income - l) * taxBracket.get(i).rate;
            }
        }
        return res;
    }
    
    public static int taxCalculator3(List<Tax> taxBracket, int income){
        //sort taxBracket descending order, large to small, null should be the last
        Collections.sort(taxBracket, new Comparator<Tax>(){
            public int compare(Tax t1, Tax t2){
                if(t1.bar == null) return 1;
                if(t2.bar == null) return -1;
                return t2.bar - t1.bar;
            }
        });
        Tax last = taxBracket.get(taxBracket.size() - 1);
        last.bar = 0;//set null to 0
        
        int res = 0;
        for(int i = 0; i < taxBracket.size(); i++){
            if(income < taxBracket.get(i).bar){
                continue;//skip
            }else if(i > 0 && income >= taxBracket.get(i - 1).bar){
                res += (taxBracket.get(i - 1).bar - taxBracket.get(i).bar) * taxBracket.get(i).rate;
            }else{
                res += (income - taxBracket.get(i).bar) * taxBracket.get(i).rate;
            }
        }
        return res;
    }
    
    //compute from large to small
}