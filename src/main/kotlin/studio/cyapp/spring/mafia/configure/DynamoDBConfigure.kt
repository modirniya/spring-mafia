package studio.cyapp.spring.mafia.configure

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DynamoDBConfiguration {

    @Bean
    fun dynamoDBMapper(): DynamoDBMapper =
        DynamoDBMapper(buildAmazonDynamoDB())

    private fun buildAmazonDynamoDB(): AmazonDynamoDB? =
        AmazonDynamoDBClientBuilder
            .standard()
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(
                    "https://dynamodb.us-east-1.amazonaws.com",
                    "us-east-1"
                )
            ).withCredentials(
                AWSStaticCredentialsProvider(
                    BasicAWSCredentials(
                           "AKIATSNNIFBSTMBXEU5L",
                           "dXdWo6JuraX+gFIohitkPE1Ls2HocfObxpWLQzd6"
                    )
                )
            ).build()
}