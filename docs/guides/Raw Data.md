![](https://securedownloads.digi.me/partners/digime/SDKReadmeBanner.png)

<p align="center">
    <a href="https://developers.digi.me/slack/join">
        <img src="https://img.shields.io/badge/chat-slack-blueviolet.svg" alt="Developer Chat">
    </a>
    <a href="../../LICENSE">
        <img src="https://img.shields.io/badge/license-apache 2.0-blue.svg" alt="Apache 2.0 License">
    </a>
    <a href="#">
    	<img src="https://img.shields.io/badge/build-passing-brightgreen.svg" 
    </a>
    <a href="https://kotlinlang.org">
        <img src="https://img.shields.io/badge/language-kotlin/java-ff69b4.svg" alt="Kotlin/Java">
    </a>
    <a href="https://developers.digi.me">
        <img src="https://img.shields.io/badge/web-digi.me-red.svg" alt="Web">
    </a>
    <a href="https://digime.freshdesk.com/support/home">
        <img src="https://img.shields.io/badge/support-freshdesk-721744.svg" alt="Support">
    </a>
</p>

<br>
# Raw Data

## Introduction

digi.me prides itself in normalising data from a huge array of sources into common ontologies, however, sometimes you may encounter data within a user's digi.me that is **unmapped**, in other words, **raw data**. In the digi.me client, this will be rendered as a raw 'data drop'. If we can deserialise this to JSON, we will show the raw JSON tree, otherwise there will be no facility to preview the data, it will only be available over the Private Sharing service - this is for security reasons.

## Pulling Raw Data

The facility to pull raw data is, much like any data type, bound by one's contract. Please [contact support](https://developers.digi.me/contact-us) to discuss having the raw data entitlement added to your contract.

### Mime Types:

When pulling raw data from the Private Sharing platform, any `DMEFile` containing raw data should be handled appropriately for that data.

For example, `DMEFile` has a `mimeType` property. This will be one case of the `DMEMimeType` enum (see [DMEMimeType.kt](https://github.com/digime/digime-sdk-android/blob/master/sdk/src/main/java/me/digi/sdk/entities/DMEMimeType.kt)).

Most instances of raw data will have the `APPLICATION_JSON` mime type, symbolising JSON data which didn't fit within existing digi.me ontology. In this case, you can invoke `fileContentAsJSON` just as you would for mapped data.

For any other mime type, `fileContentAsJSON` will throw an exception. In such cases, it should not be used and you should instead access the `ByteArray` file content directly with the `fileContent` property and handle it how you see fit for your use case.

## Pushing Raw Data

If you push data to a Postbox that isn't pre-mapped to our [Ontology](https://developers.digi.me/reference-api), it will be treated as raw data and pushed into a user's digi.me as such. The metadata supplied with the push will be used to deduce the data's mime type going forward (IE the mime type the file has when pulled back down).

Please see the [Postbox Documentation](./postbox.html) for more information on pushing data into digi.me.