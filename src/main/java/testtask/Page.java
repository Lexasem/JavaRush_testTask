package testtask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import testtask.controllers.entities.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Объект который передаётся в фронд энд в виде json
 *
 */
public class Page {
    public final static int itemsPerPage = 10; //количество элементов на страницу todo вынести itemsPerPage в .properties file
    public List<Entity> entities; //объекты из базы данных
    public int curentPage, pagesCount; //curentPage - номер текущей страницы, pagesCount - общее число страниц
    public Page(){
        this.entities = new ArrayList<Entity>();
    }

    public void countPages(long entitiesCount){
        pagesCount = (int)(entitiesCount/itemsPerPage);
        if(entitiesCount%itemsPerPage > 0) pagesCount++;
    }

    public String toJson(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return "";
        }
    }
}
