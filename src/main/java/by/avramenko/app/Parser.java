package by.avramenko.app;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private int count;
    private String url1;
    private String url2;
    private ArrayList<Product> productList;

    public Parser() {
        this.count = 0;
        this.url1 = "https://www.scalemates.com/search.php?q=*&sortby=date&page=news&fkSECTION[]=Kits&fkYEAR[]=2017&fkYEAR[]=2018&fkYEAR[]=2019&mode=ajax&start=";
        this.url2 = "&df=ok";
        this.productList = new ArrayList<Product>();
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    private String getUrl(int i) {
        if (i >= 0)
            return url1 + i + url2;
        else
            return "";
    }

    public void parse(int n) throws IOException {
        productList.clear();
        while (count < n) {
            Document doc;
            doc = Jsoup.connect(getUrl(count)).timeout(10*1000).get();
            Elements elements = doc.select("div.ac");
            for (Element element : elements) {
                if (count < n) {
                    productList.add(parseElement(element));
                }
            }
        }
    }

    public void parse() throws IOException {
        parse(100);
    }

    private Product parseElement(Element el) throws IOException {
        Product product = new Product();
        String scalematesUrl = el.select("div.ar > a").first().attr("href");
        product.setScalematesUrl(scalematesUrl);

        String brandAndScale = (el.select("div.ar").html().split("<br>"))[1];
        String brand = deleteN((brandAndScale.split("1"))[0]);
        int scale = 1;
        if (brandAndScale.contains(":"))
            try {
                scale = Integer.parseInt(deleteN(((brandAndScale.split(":"))[1])));
            } catch (NumberFormatException ex) {
                ex.getMessage();
            }
        else
            scale = 1;

        product.setBrand(brand);
        product.setScale(scale);

        String brandCatno = clear(((el.select("div.ar").html().split("<br>"))[2].split("\n"))[0].toUpperCase());
        product.setBrandCatno(brandCatno);

        String name = el.select("div.ar > a").text() + " " + el.select("div.ar > em").text();
        product.setName(name);

        String boxartUrl = deleteSize(el.select("a.al > img").attr("src"));
        product.setBoxartUrl(boxartUrl);

        String yearAndDescription = el.select("div.ar > div.nw").text();
        String description = "";
        String year = "";

        if (yearAndDescription.contains("|")) {
            int i = yearAndDescription.indexOf('|');
            year = yearAndDescription.substring(0, i);
            description = yearAndDescription.substring(i + 1);
        } else
            year = yearAndDescription;
        product.setDescription(description);
        product.setYear(year);

        this.count++;
        System.out.println("------------ " + count);
        return product;
    }

    private String deleteN(String str) {
        if (str.contains("\n")) {
            int pos = str.indexOf("\n");
            return str.substring(0, pos) + str.substring(pos + 1);
        } else
            return str;
    }

    private String clear(String str) {
        String result = "";
        for (String s : str.split(" ")) {
            result += s;
        }
        return result;
    }

    private String deleteSize(String str) {
        if (str.contains("-"))
            return str.substring(0, str.lastIndexOf("-"));
        else
            return str;
    }

}
