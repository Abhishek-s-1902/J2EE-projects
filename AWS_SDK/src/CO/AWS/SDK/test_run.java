package CO.AWS.SDK;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;

public class test_run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AmazonSNSClient snsClient = new AmazonSNSClient();		                           
		snsClient.setRegion(Region.getRegion(Regions.US_WEST_2));

		String topicArn = "arn:aws:sns:us-west-2:052427152110:testing_message";
		
		String msg = "My text published to SNS topic with email endpoint";
		PublishRequest publishRequest = new PublishRequest(topicArn, msg);
		PublishResult publishResult = snsClient.publish(publishRequest);
		//print MessageId of message published to SNS topic
		System.out.println("MessageId - " + publishResult.getMessageId());
		
		System.out.println("testing");
		
	}

}
