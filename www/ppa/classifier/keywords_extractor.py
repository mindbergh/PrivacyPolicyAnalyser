import nltk

pii = {"name"                   : 0,
       "home address"           : 1,
       "email address"          : 2,
       "e-mail address"         : 2,
       "national identification": 3,
       "passport number"        : 4,
       "IP address"             : 5,
       "vehicle registration"   : 6,
       "license"                : 7,
       "face"                   : 8,
       "fingerprints"           : 9,
       "handwriting"            : 10,
       "credit card"            : 11,
       "birth"                  : 12,
       "birthplace"             : 13,
       "genetic information"    : 14,
       "telephone number"       : 15,
       "SSN"                    : 16
       }

non_pii = {
    "country"         : 0,
    "state"           : 1,
    "postcode"        : 2,
    "city"            : 3,
    "age"             : 4,
    "gender"          : 5,
    "race"            : 6,
    "grades"          : 7,
    "salary"          : 8,
    "job"             : 9,
    "position"        : 10,
    "criminal record" : 11,
    "cookie"          : 12,
    "cookies"         : 12,
    "location"        : 13,
    "web history"     : 14,
    "service provider": 15,
    "OS"              : 16,
    "web browser"     : 17
}


def parse_segment(sgmt):
    tokens = nltk.word_tokenize(sgmt)
    parsed_pii = [0] * len(pii)
    parsed_non_pii = [0] * len(non_pii)
    for i in xrange(0, len(tokens)):
        for key in pii:
            if key == tokens[i].lower():
                parsed_pii[pii[key]] = 1
            if i < len(tokens) - 1:
                two_tokens = tokens[i].lower() + ' ' + tokens[i + 1].lower()
                if two_tokens == key:
                    parsed_pii[pii[key]] = 1

        for key in non_pii:
            if key == tokens[i].lower():
                parsed_non_pii[non_pii[key]] = 1
            if i < len(tokens) - 1:
                two_tokens = tokens[i].lower() + ' ' + tokens[i + 1].lower()
                if two_tokens == key:
                    parsed_non_pii[non_pii[key]] = 1

    print parsed_pii
    print parsed_non_pii


if __name__ == '__main__':
    sgmt = """Information you provide. You are not required to create a Lumosity account to gain access to many areas of Lumosity. If you do create an account, we ask for certain information to process your registration, including an email address and password. If you subscribe to a paid account, we request additional information, including your full name and payment method details.
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
