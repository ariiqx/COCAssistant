package com.videdesk.mobile.cocassistant.config;

public class AWS {

    public void createNews() {
        /*
        final NewsDO newsItem = new NewsDO();

        newsItem.setUserId(identityManager.getCachedUserID());

        newsItem.setArticleId("Article1");
        newsItem.setContent("This is the article content");

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newsItem);
                // Item saved
            }
        }).start();
        */
    }

    public void readNews() {
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {

                NewsDO newsItem = dynamoDBMapper.load(
                        NewsDO.class,
                        identityManager.getCachedUserID(),
                        "Article1");

                // Item read
                // Log.d("News Item:", newsItem.toString());
            }
        }).start();
        */
    }

    public void updateNews() {
        /*
        final NewsDO newsItem = new NewsDO();

        newsItem.setUserId(identityManager.getCachedUserID());

        newsItem.setArticleId("Article1");
        newsItem.setContent("This is the updated content.");

        new Thread(new Runnable() {
            @Override
            public void run() {

                dynamoDBMapper.save(newsItem);

                // Item updated
            }
        }).start();
        */
    }

    public void deleteNews() {
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {

                NewsDO newsItem = new NewsDO();

                newsItem.setUserId(identityManager.getCachedUserID());    //partition key

                newsItem.setArticleId("Article1");  //range (sort) key

                dynamoDBMapper.delete(newsItem);

                // Item deleted
            }
        }).start();
        */
    }

    public void queryNews() {
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                NewsDO news = new NewsDO();
                news.setUserId(identityManager.getCachedUserID());
                news.setArticleId("Article1");

                Condition rangeKeyCondition = new Condition()
                        .withComparisonOperator(ComparisonOperator.BEGINS_WITH)
                        .withAttributeValueList(new AttributeValue().withS("Trial"));

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(note)
                        .withRangeKeyCondition("articleId", rangeKeyCondition)
                        .withConsistentRead(false);

                PaginatedList<NewsDO> result = dynamoDBMapper.query(NewsDO.class, queryExpression);

                Gson gson = new Gson();
                StringBuilder stringBuilder = new StringBuilder();

                // Loop through query results
                for (int i = 0; i < result.size(); i++) {
                    String jsonFormOfItem = gson.toJson(result.get(i));
                    stringBuilder.append(jsonFormOfItem + "\n\n");
                }

                // Add your code here to deal with the data result
                Log.d("Query result: ", stringBuilder.toString());

                if (result.isEmpty()) {
                    // There were no items matching your query.
                }
            }
        }).start();
        */
    }
}
