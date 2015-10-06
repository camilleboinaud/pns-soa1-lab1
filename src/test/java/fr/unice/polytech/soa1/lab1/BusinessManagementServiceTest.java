package fr.unice.polytech.soa1.lab1;

import fr.unice.polytech.soa1.lab1.business.Item;
import fr.unice.polytech.soa1.lab1.utils.ContentType;
import fr.unice.polytech.soa1.lab1.utils.exceptions.ContentNotFoundException;
import fr.unice.polytech.soa1.lab1.ws.BusinessManagementService;
import fr.unice.polytech.soa1.lab1.ws.impl.BusinessManagementServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Tianhao on 10/6/2015.
 */
public class BusinessManagementServiceTest extends TestCase {
    private BusinessManagementService service = null;
    protected void setUp() {
        service = new BusinessManagementServiceImpl();
    }

    @Test
    public void test_management() throws ContentNotFoundException {
        Item newTestItem = service.createContent(ContentType.ITEM, 20.1, "test_item", "desc_test_item");
        assertTrue(Storage.read(ContentType.ITEM,newTestItem.getId()) != null);
        assertTrue(service.listContents(ContentType.ITEM).contains(newTestItem));
        assertEquals(service.displayContent(ContentType.ITEM, newTestItem.getId()),newTestItem);
    }
}
