package purchasingmanagementsystem

import org.apache.commons.lang3.StringUtils

class ValidatorUtil {
    static def isAValidIdentityDocument(String identityDocument)
    {
        if (!StringUtils.isNumeric(identityDocument)) return false

        int totalVerifier = 0
        identityDocument = identityDocument.replace("-", "")
        int identityDocumentLength = identityDocument.trim().length()
        def multiplierDigits = [ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1 ]

        if (identityDocumentLength < 11 || identityDocumentLength > 11)
            return false

        for (int digitVerifier = 1; digitVerifier <= identityDocumentLength; digitVerifier++)
        {
            int verifierCalculation = Integer.parseInt(customSubString(identityDocument, digitVerifier - 1, 1)) * multiplierDigits[digitVerifier - 1]

            if (verifierCalculation < 10)
                totalVerifier += verifierCalculation
            else
                totalVerifier += Integer.parseInt(customSubString(verifierCalculation.toString(), 0, 1)) + Integer.parseInt(customSubString(verifierCalculation.toString(), 1, 1))
        }

        if (totalVerifier % 10 == 0)
            return true
        else
            return false
    }

    static def isAValidJuridicTaxpayerIdentificationNumber(String juridicTaxpayerIdentificationNumber)
    {
        if (!StringUtils.isNumeric(juridicTaxpayerIdentificationNumber)) return false

        int totalVerifier = 0

        def multiplierDigits =  [7, 9, 8, 6, 5, 4, 3, 2 ]

        juridicTaxpayerIdentificationNumber = juridicTaxpayerIdentificationNumber.replace("-", "").replace(" ", "")

        String digitVerifier = customSubString(juridicTaxpayerIdentificationNumber, 8, 1)

        if (juridicTaxpayerIdentificationNumber.length() == 9)
            if (!"145".contains(customSubString(juridicTaxpayerIdentificationNumber, 0, 1)))
                return false

        for (int digitToVerify = 1; digitToVerify <= 8; digitToVerify++)
        {
            int verifierCalculation = Integer.parseInt(customSubString(juridicTaxpayerIdentificationNumber, digitToVerify - 1, 1)) * multiplierDigits[digitToVerify - 1]
            totalVerifier += verifierCalculation
        }

        return (totalVerifier % 11 == 0 && digitVerifier == "1" || totalVerifier % 11 == 1 && digitVerifier == "1" || (11 - (totalVerifier % 11)) == digitVerifier)
    }

    static def customSubString(String string, int start, int length) {
        return string.substring(start, Math.min(start + length, string.length()))
    }
}
