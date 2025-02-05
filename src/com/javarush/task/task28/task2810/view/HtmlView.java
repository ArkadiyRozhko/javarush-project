package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;


import java.io.*;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlView implements View{
    private Controller controller;
    private final String filePath="./src/" + this.getClass().getPackage().getName().replaceAll("[.]", "/") + "/vacancies.html";
    @Override
    public void update(List<Vacancy> vacancies) {
        String fileBody=getUpdatedFileContent(vacancies);
        updateFile(fileBody);
    }

    @Override
    public void setController(Controller controller) {
        this.controller=controller;
    }
    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }
    private String getUpdatedFileContent(List<Vacancy>vacancies){
        try {
            Document doc = getDocument();
            Elements templateHidden = doc.getElementsByClass("template");
            Element template = templateHidden.clone().removeAttr("style").removeClass("template").get(0);

            //remove all prev vacancies
            Elements prevVacancies = doc.getElementsByClass("vacancy");

            for (Element redundant : prevVacancies) {
                if (!redundant.hasClass("template")) {
                    redundant.remove();
                }
            }

            //add new vacancies
            for (Vacancy vacancy : vacancies) {
                Element vacancyElement = template.clone();

                Element vacancyLink = vacancyElement.getElementsByAttribute("href").get(0);
                vacancyLink.appendText(vacancy.getTitle());
                vacancyLink.attr("href", vacancy.getUrl());
                Element city = vacancyElement.getElementsByClass("city").get(0);
                city.appendText(vacancy.getCity());
                Element companyName = vacancyElement.getElementsByClass("companyName").get(0);
                companyName.appendText(vacancy.getCompanyName());
                Elements salaryEls = vacancyElement.getElementsByClass("salary");
                Element salary = salaryEls.get(0);
                salary.appendText(vacancy.getSalary());

                templateHidden.before(vacancyElement.outerHtml());
            }
            return doc.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Some exception occurred";
    }
    private void updateFile(String string){
        File file=new File(filePath);
        try (FileOutputStream outputStream=new FileOutputStream(file)){
            outputStream.write(string.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected Document getDocument() throws IOException{
        File file=new File(filePath);
        Document document=Jsoup.parse(file,"UTF-8");
        return document;
    }
}
