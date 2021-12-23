package studio.cyapp.spring.mafia.layer

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import studio.cyapp.spring.mafia.entity.RootElement

@Repository
class DynamoDBRepo {

    @Autowired
    private lateinit var mapper: DynamoDBMapper

    fun retrieve(pk: String, sk: String): RootElement? =
        mapper.load(RootElement::class.java, pk, sk)

    fun delete(pk: String, sk: String): RootElement? =
        retrieve(pk, sk).also { mapper.delete(it) }

    fun store(item: RootElement): RootElement =
        item.also { mapper.save(it) }

    fun query(expression: DynamoDBQueryExpression<RootElement>)
            : PaginatedQueryList<RootElement> =
        mapper.query(RootElement::class.java, expression)


}