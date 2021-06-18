import java.util.Scanner;

public class IMP2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner strsc = new Scanner(System.in);
        City[] cityList;
        int test = sc.nextInt();
        for (int i = 1; i<=test;i++){
            int cityNumber = sc.nextInt();
            cityList = new City[cityNumber+1];
            for (int x = 1; x<=cityNumber;x++){
                cityList[x]=new City();
                cityList[x].name=strsc.nextLine();
                cityList[x].indexCity=x;
                int neighborsNumber = sc.nextInt();
                cityList[x].path = new Path[neighborsNumber];
                for (int y = 0;y<neighborsNumber;y++){
                    cityList[x].path[y]=new Path();
                    cityList[x].path[y].indexCity = sc.nextInt();
                    cityList[x].path[y].costPath = sc.nextInt();
                }
            }
            int NumberPathSearch=sc.nextInt();
            String[] townName = new String[NumberPathSearch*2];
            for (int x = 0; x<NumberPathSearch;x++){
                String[] s = strsc.nextLine().split(" ");
                townName[x*2] = s[0];
                townName[x*2+1]=s[1];
            }
            for (int x = 0; x<NumberPathSearch;x++){
                getShortedPath(townName[x*2],townName[x*2+1],cityList);
            }
        }
    }

    private static void getShortedPath(String start, String end, City[] CityList) {
        // search start city and change position first and him
        for(int i = 1;i<CityList.length;i++){
            CityList[i].costPath=Integer.MAX_VALUE; // cost path to the all city INT.MaxVal
            if(CityList[i].name.equals(start)){
                CityList[0]=CityList[1];
                CityList[1]=CityList[i];
                CityList[i]=CityList[0];
                CityList[1].costPath=0;
            }
        }

        for (int i = 1;i<CityList.length;i++){
            for (Path path:CityList[i].path){ // finding ways out of town
                for (int x = 1;x<CityList.length;x++){
                    if ((path.indexCity==CityList[x].indexCity)&&(CityList[x].costPath> CityList[i].costPath+path.costPath))// If you can get there by the shortest path, change the cost of the path
                    {
                        CityList[x].costPath=CityList[i].costPath+path.costPath;
                        break;
                    }
                }
            }
        }
        for (int i=1;i<CityList.length;i++){
            if(CityList[i].name.equals(end)){
                System.out.println(CityList[i].costPath);
            }
        }
    }
}


class City{
Path[] path;
String name;
int indexCity;
int costPath; // cost path to city
}
class  Path{
    int indexCity; // which city
    int costPath; // cost
}
