package com.alchemist;

import com.alchemist.utils.ExcelUtils;
import com.alchemist.utils.JsonUtils;
import com.alchemist.utils.Payload;
import com.alchemist.utils.Resources;
import com.alchemist.utils.SpecFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddBookExcelSpecBuilderTest {

    @DataProvider(name = "BooksData")
    public Object[][] getData() {
        return ExcelUtils.readBooksFromResource("testRestAssured.xlsx", "BooksData");
    }

    @Test(dataProvider = "BooksData")
    public void addBookThenDeleteBook_usingSpecBuilder(String isbn, String aisle) {

        // 1) ADD BOOK
        String addRes =
                given()
                    .spec(SpecFactory.requestSpec())
                    .body(Payload.addBook(isbn, aisle))
                    .log().all()
                .when()
                    .post(Resources.ADD_BOOK)
                .then()
                    .log().all()
                    .spec(SpecFactory.success200Json())
                    .extract().asString();

        String msg = JsonUtils.getValueAsString(addRes, "Msg");
        String idFromRes = JsonUtils.getValueAsString(addRes, "ID");

        // Fallback if ID not returned for some reason
        String expectedId = isbn + aisle;
        String idToDelete = (idFromRes != null && !idFromRes.isBlank()) ? idFromRes : expectedId;

        Assert.assertNotNull(msg, "Msg should not be null");
        Assert.assertNotNull(idToDelete, "ID should not be null");

        // Handle both valid cases
        boolean successAdd = msg.toLowerCase().contains("successfully added");
        boolean alreadyExists = msg.toLowerCase().contains("already exists");

        if (!(successAdd || alreadyExists)) {
            Assert.fail("Unexpected Msg from AddBook: " + msg + " | Response: " + addRes);
        }

        // 2) DELETE BOOK (works for both added + already exists)
        String delRes =
                given()
                    .spec(SpecFactory.requestSpec())
                    .body(Payload.deleteBook(idToDelete))
                    .log().all()
                .when()
                    .post(Resources.DELETE_BOOK)
                .then()
                    .log().all()
                    .spec(SpecFactory.success200Json())
                    .extract().asString();

        // Delete response usually returns: {"msg":"book is successfully deleted"}
        String delMsg = JsonUtils.getValueAsString(delRes, "msg");
        Assert.assertNotNull(delMsg, "Delete msg should not be null. Response: " + delRes);
        Assert.assertTrue(delMsg.toLowerCase().contains("successfully deleted"),
                "Delete msg mismatch. Actual: " + delMsg);
    }
}
