# -*- coding: UTF-8 -*-
import nltk
topic = {}
topic[0] = set(['name'])
topic[1] = set(['home address','residence'])
topic[2] = set(['e-mail address',"email address"])
topic[3] = set(["national identification","identification number"])
topic[4] = set(["passport"])
topic[5] = set(["ip"])
topic[6] = set(["vehicle registration"])
topic[7] = set(["driver's license"])
topic[8] = set(["face"])
topic[9] = set(["fingerprints"])
topic[10] = set(["handwriting"])
topic[11] = set(["credit card"])
topic[12] = set(["birth","birthday"])
topic[13] = set(["birthplace"])
topic[14] = set(["genetic information"])
topic[15] = set(["telephone number","phone number"])
topic[16] = set(["ssn","social security"])
topic[17] = set(["country","nation"])
topic[18] = set(["state"])
topic[19] = set(["postcode","zip code"])
topic[20] = set(["city"])
topic[21] = set(["age"])
topic[22] = set(["gender"])
topic[23] = set(["race"])
topic[24] = set(["grades"])
topic[25] = set(["salary"])
topic[26] = set(["job"])
topic[27] = set(["position"])
topic[28] = set(["criminal record"])
topic[29] = set(["cookie","cookies"])
topic[30] = set(["web history"])
topic[31] = set(["service provider"])
topic[32] = set(["os","operating system"])
topic[33] = set(["web browser"])

def parse_segment(sgmt):
	tokens = nltk.word_tokenize(sgmt)
	parsed_list= [0]* len(topic)
	for i in xrange(0,len(tokens)):
	    for k in range(0,len(topic) ):
	        if tokens[i].lower() in topic[k]:
	            parsed_list[k]= 1
	        if i<len(tokens)-1:
	            two_tokens= tokens[i].lower()+' '+tokens[i+1].lower()
	            if two_tokens in topic[k]:
	                  parsed_list[k]= 1

	print parsed_list

if __name__ == '__main__':

     sgmt="""Information you provide. You are not required to create a Lumosity account to gain access to many areas of Lumosity. If you do create an account, we ask for certain information to process your registration, including an email address and password. If you subscribe to a paid account, we request additional information, including your full name and payment method details.
     To help personalize your experience, we also ask for certain, limited demographic information, including your first name, gender, date of birth and education level. Providing most of this information is optional. We also allow you to upload a photo of yourself and select your preferences for training, language, and receiving email communications.
     If you sign in to Lumosity using your Facebook or other third party credentials, we will use that service to authenticate you. We may also collect other information that you have agreed may be provided by that third party, such as your username, name, e-mail address, date of birth and gender. We collect this information so that it can be used for the purposes explained in this Policy.
     We may supplement the information you provide to us with additional information gathered from other sources, such as publicly available information.
     Data we collect. In addition to the information you provide to us, when you use Lumosity, we may also automatically collect and store information about the computer, mobile device, or other devices you use to access Lumosity and about how you use Lumosity. For example, we collect and store data about the games you play and your performance in those games. We may also collect and store information such as your browser type, IP address, language, operating system, unique device identifier, the date and time of your visit, the pages you view and the websites you visited immediately before and after visiting Lumosity.
     Our use of cookies. To collect information, we use cookies and other standard web technologies (e.g., pixel tags). Cookies are small text files placed on the browser of your computer or mobile device when you visit a website that collect non-personal information. We use “persistent cookies” to save your login information for future logins to Lumosity. We use “session ID cookies” to enable certain features of Lumosity, to better understand how you interact with the Service and to monitor aggregate usage and web traffic routing on the Service. Most browsers automatically accept cookies, but allow you to disable cookies through the “options” or “preferences” menu of your browser. Please be aware that disabling cookies could interfere with the functionality of Lumosity.
     Third parties whose products or services are accessible in Lumosity, including social networking services like Facebook, may also use cookies or similar tools. Please review their privacy policies for information about their cookies and other privacy practices.
     The third-party advertising and analytics companies with whom we or our advertisers partner, including Google and New Relic, may place a unique cookie or utilize similar technologies on your browser in order to collect non-personal information about your visits to Lumosity. If you would like more information about this practice and how to control how this information is used by these companies, please visit the following links:
     http://www.networkadvertising.org/choice
     http://www.aboutads.info"""
     parse_segment(sgmt)