Action()
{

	web_set_sockets_option("SSL_VERSION", "AUTO");

	web_add_header("A-IM", 
		"x-bm,gzip");

	web_add_auto_header("Sec-Fetch-Dest", 
		"empty");

	web_add_auto_header("Sec-Fetch-Mode", 
		"no-cors");

	web_add_auto_header("Sec-Fetch-Site", 
		"none");

	web_url("seed", 
		"URL=https://clientservices.googleapis.com/chrome-variations/seed?osname=win&channel=stable&milestone=88", 
		"Resource=0", 
		"Referer=", 
		"Snapshot=t65.inf", 
		"Mode=HTML", 
		EXTRARES, 
		"Url=https://ssl.gstatic.com/safebrowsing/csd/client_model_v5_variation_6.pb", "Referer=", ENDITEM, 
		LAST);

	lr_start_transaction("Vu_LaunchApp_transaction");

	web_add_header("X-Goog-Update-AppId", 
		"ihnlcenocehgdaegdmhbidjhnhdchfmm,oimompecagnajdejgnnjijobebaeigek,hnimpnehoodheedghdeeijklkeaacbdc,gcmjkmgdlgnkkcocmoeiminaijmmjnii,cmahhnpholdijhjokonmfdjbfmklppij,obedbbhbpmojnkanicioggnmelmoomoc,kiabhabjdbkjdpjbpigfodbdjmbglcoo,giekcmmlnklenlaomppkphknjmnnpneh,khaoiebndkojlmppeemjhbpbandiljpe,hfnkpimlhhgieaddgfemjhofmfblmnib,llkgjffcdpffmhiakmfcdcblohccpfmo,aemomkdncapdnfajjbbcbdebjljbpmpj,gkmgaooipdjhmangpemjhigmamcehddo,ehgidpndbllacpjalkiimkbadgjfnnmc,jflookgnkcckhobaglndicnbbgbonegd,"
		"ggkkehgbnfjpeggfpleeakpidbkibbmn,bklopemakmnopmghhmccadeonafabnal,jamhcnnkihinmdlkakkaopbjbbcngflc,ojhpjlocmbogdgmfpkhlaaeamibhnphh,eeigpngbgcognadeebkilcpcaedhellh");

	web_add_header("X-Goog-Update-Interactivity", 
		"bg");

	web_add_header("X-Goog-Update-Updater", 
		"chrome-88.0.4324.182");

	web_custom_request("json", 
		"URL=https://update.googleapis.com/service/update2/json?cup2key=10:741554374&cup2hreq=a2b5c4b99fb396c626ca469c2ba21004e5b9e16f065f0b500b8a21570ac7d4bd", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=", 
		"Snapshot=t66.inf", 
		"Mode=HTML", 
		"EncType=application/json", 
		"Body={\"request\":{\"@os\":\"win\",\"@updater\":\"chrome\",\"acceptformat\":\"crx2,crx3\",\"app\":[{\"appid\":\"ihnlcenocehgdaegdmhbidjhnhdchfmm\",\"brand\":\"GCEU\",\"enabled\":true,\"ping\":{\"ping_freshness\":\"{71e506c3-5dd1-474a-9d8e-0c9402de8488}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"0.0.0.0\"},{\"appid\":\"oimompecagnajdejgnnjijobebaeigek\",\"brand\":\"GCEU\",\"enabled\":true,\"ping\":{\"ping_freshness\":\"{cb41fe64-59c7-4121-939f-186c843e2818}\",\"rd\":5165},\"updatecheck\":{},\""
		"version\":\"4.10.1679.0\"},{\"appid\":\"hnimpnehoodheedghdeeijklkeaacbdc\",\"brand\":\"GCEU\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\"1.6f6bc93dcd62dc251850d2ff458fda96083ceb7fbe8eeb11248b8485ef2aea23\"}]},\"ping\":{\"ping_freshness\":\"{1a5ca421-db8d-4f95-8ca7-15268a0a0e22}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"0.57.44.2492\"},{\"appid\":\"gcmjkmgdlgnkkcocmoeiminaijmmjnii\",\"brand\":\"GCEU\",\"cohort\":\"1:bm1:\",\"cohorthint\":\"M54ToM99\",\"cohortname\":\"M54ToM99\",\""
		"enabled\":true,\"packages\":{\"package\":[{\"fp\":\"1.869f6197c3fdd474910319ff37ee13b73f8fb8ceeaaa62517e2d056b6a03ff54\"}]},\"ping\":{\"ping_freshness\":\"{8dec12c0-c04c-4d31-977e-4a935a4b99b7}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"9.19.0\"},{\"appid\":\"cmahhnpholdijhjokonmfdjbfmklppij\",\"brand\":\"GCEU\",\"cohort\":\"1:wr3:x23@0.01\",\"cohorthint\":\"Auto\",\"cohortname\":\"Auto\",\"enabled\":true,\"ping\":{\"ping_freshness\":\"{43165ed0-d19b-4133-8814-683f5f312aa5}\",\"rd\":5165},\""
		"updatecheck\":{},\"version\":\"0.0.0.0\"},{\"accept_locale\":\"ENUS\",\"appid\":\"obedbbhbpmojnkanicioggnmelmoomoc\",\"brand\":\"GCEU\",\"cohort\":\"1:s6f:\",\"cohorthint\":\"Auto\",\"cohortname\":\"Auto\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\"1.d7a0daa17084ba0e9862c059a74285ac0eda392411dbc844c771995709e5dd70\"}]},\"ping\":{\"ping_freshness\":\"{91d28b0e-830c-4ae1-ac9b-b329726661ad}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"20210207.356485955\"},{\"appid\":\""
		"kiabhabjdbkjdpjbpigfodbdjmbglcoo\",\"brand\":\"GCEU\",\"cohort\":\"1:v3l:\",\"cohorthint\":\"Auto\",\"cohortname\":\"Auto\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\"1.d5d11284fd7f1f100e83d84a6517db52c6711f4dec784f9bd5b89220ea8d20a8\"}]},\"ping\":{\"ping_freshness\":\"{2afcf05e-0717-4e6b-bb8f-b70d15ca1798}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"2021.2.20.6\"},{\"appid\":\"giekcmmlnklenlaomppkphknjmnnpneh\",\"brand\":\"GCEU\",\"cohort\":\"1:j5l:\",\"cohorthint\":\"Auto\",\""
		"cohortname\":\"Auto\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\"1.fd515ec0dc30d25a09641b8b83729234bc50f4511e35ce17d24fd996252eaace\"}]},\"ping\":{\"ping_freshness\":\"{7ac9e18c-3464-452c-a086-743497a9e2dc}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"7\"},{\"appid\":\"khaoiebndkojlmppeemjhbpbandiljpe\",\"brand\":\"GCEU\",\"cohort\":\"1:cux:\",\"cohorthint\":\"Auto\",\"cohortname\":\"Auto\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\""
		"1.ffd1d2d75a8183b0a1081bd03a7ce1d140fded7a9fb52cf3ae864cd4d408ceb4\"}]},\"ping\":{\"ping_freshness\":\"{3c737e26-f9ba-49c4-a181-5c69ec0b57bd}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"43\"},{\"appid\":\"hfnkpimlhhgieaddgfemjhofmfblmnib\",\"brand\":\"GCEU\",\"cohort\":\"1:jcl:\",\"cohorthint\":\"Auto\",\"cohortname\":\"Auto\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\"1.0432622697f8077015dbd2c020fb2d5e61670a1c3df68f58050f8e955e61e97b\"}]},\"ping\":{\"ping_freshness\":\""
		"{5ef83db0-0fe2-438e-8b49-fc3df51e0843}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"6440\"},{\"appid\":\"llkgjffcdpffmhiakmfcdcblohccpfmo\",\"brand\":\"GCEU\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\"1.d730fdd6875bfda19ae43c639e89fe6c24e48b53ec4f466b1d7de2001f97e03c\"}]},\"ping\":{\"ping_freshness\":\"{6d58adc3-884b-4518-872f-03b29bf30107}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"1.0.0.6\"},{\"appid\":\"aemomkdncapdnfajjbbcbdebjljbpmpj\",\"brand\":\"GCEU\",\"enabled\":true,"
		"\"ping\":{\"ping_freshness\":\"{fac59ac5-7101-4665-a77c-fa2b8bfac493}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"1.0.5.0\"},{\"appid\":\"gkmgaooipdjhmangpemjhigmamcehddo\",\"brand\":\"GCEU\",\"cohort\":\"1:qe3:\",\"cohorthint\":\"Canary\",\"cohortname\":\"Stable\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\"1.78572f8178a695ecbf22a4e10a52e81cf0ae95a5a9f79ead6b8c58339178f02e\"}]},\"ping\":{\"ping_freshness\":\"{dcbaa03a-cb01-4af7-8933-cd7cf1eaa2b5}\",\"rd\":5165},\"tag\":\""
		"canary_eset_b\",\"updatecheck\":{},\"version\":\"88.253.200\"},{\"appid\":\"ehgidpndbllacpjalkiimkbadgjfnnmc\",\"brand\":\"GCEU\",\"cohort\":\"1:ofl:\",\"cohorthint\":\"stable64\",\"cohortname\":\"stable64\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\"1.a8a79d350c2a5e3bc36226633a8e0bed0dfab184e77f38fc8f0820ebacf8eafc\"}]},\"ping\":{\"ping_freshness\":\"{6c39861d-5adf-47d4-85d5-54eb72172485}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"2018.8.8.0\"},{\"appid\":\""
		"jflookgnkcckhobaglndicnbbgbonegd\",\"brand\":\"GCEU\",\"cohort\":\"1:s7x:\",\"cohorthint\":\"Auto\",\"cohortname\":\"Auto\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\"1.a8c99d0d4f79421d68922346b9178ec77e710759f7e985ccafea273dc8a330d9\"}]},\"ping\":{\"ping_freshness\":\"{6f6d7c6e-f0d4-455f-9a86-fc3c61761967}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"2570\"},{\"appid\":\"ggkkehgbnfjpeggfpleeakpidbkibbmn\",\"brand\":\"GCEU\",\"cohort\":\"1:ut9:\",\"cohorthint\":\"M80ToM99\",\""
		"cohortname\":\"M80ToM99\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\"1.6f6d8e181250235f5a64e27f021a3d68ab6e9a32906a221f8922f51a179da4b6\"}]},\"ping\":{\"ping_freshness\":\"{4f83bdab-296b-4dff-9da5-b65eb6cc0663}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"2021.2.15.1201\"},{\"appid\":\"bklopemakmnopmghhmccadeonafabnal\",\"brand\":\"GCEU\",\"cohort\":\"1:swl:\",\"cohorthint\":\"Auto\",\"cohortname\":\"Auto\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\""
		"1.70497f45af368f6d591eb9b93a097b7b56821b0770ee00f04b2f5901487a0421\"}]},\"ping\":{\"ping_freshness\":\"{6ae08b8f-973d-4868-89af-8cb5f2e2ab8f}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"4\"},{\"appid\":\"jamhcnnkihinmdlkakkaopbjbbcngflc\",\"brand\":\"GCEU\",\"cohort\":\"1:wvr:\",\"cohorthint\":\"Auto\",\"cohortname\":\"Auto\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\"1.92c94ad7e842df73bd5f37c3a7db6ca7505dcccc2ff6ac04586e798c53366da4\"}]},\"ping\":{\"ping_freshness\":\""
		"{09ea7068-b3d9-4e83-ba92-c282389ddf24}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"90.0.4424.0\"},{\"appid\":\"ojhpjlocmbogdgmfpkhlaaeamibhnphh\",\"brand\":\"GCEU\",\"cohort\":\"1:w0x:\",\"cohorthint\":\"Auto\",\"cohortname\":\"Auto\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\"1.478aa915e78878e332a0b4bb4d2a6fb67ff1c7f7b62fe906f47095ba5ae112d0\"}]},\"ping\":{\"ping_freshness\":\"{0cdb3957-7863-4ce3-b695-1e5ede23ecfa}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"1\"},{\"appid\":\""
		"eeigpngbgcognadeebkilcpcaedhellh\",\"brand\":\"GCEU\",\"cohort\":\"1:w59:\",\"cohorthint\":\"Auto\",\"cohortname\":\"Auto\",\"enabled\":true,\"packages\":{\"package\":[{\"fp\":\"1.c64c9c1008f3ba5f6e18b3ca524bc98dcd8acfae0a2720a8f1f3ef0f8d643d05\"}]},\"ping\":{\"ping_freshness\":\"{6e31ff53-ee19-4761-b7e0-c1c7e7f3b71f}\",\"rd\":5165},\"updatecheck\":{},\"version\":\"2020.11.2.164946\"}],\"arch\":\"x64\",\"dedup\":\"cr\",\"domainjoined\":true,\"hw\":{\"physmemory\":4},\"lang\":\"en-US\",\"nacl_arch"
		"\":\"x86-64\",\"os\":{\"arch\":\"x86_64\",\"platform\":\"Windows\",\"version\":\"10.0.19042.804\"},\"prodversion\":\"88.0.4324.182\",\"protocol\":\"3.1\",\"requestid\":\"{213c17ae-1197-42fa-b1fd-fea2d76f44a7}\",\"sessionid\":\"{fcf76159-fa26-44ec-8419-d6e6c99e4fd1}\",\"updater\":{\"autoupdatecheckenabled\":true,\"ismachine\":true,\"lastchecked\":0,\"laststarted\":0,\"name\":\"Omaha\",\"updatepolicy\":-1,\"version\":\"1.3.36.72\"},\"updaterversion\":\"88.0.4324.182\"}}", 
		LAST);

	web_add_auto_header("Sec-Fetch-Mode", 
		"navigate");

	web_add_auto_header("Sec-Fetch-Dest", 
		"document");

	web_add_cookie("AMCV_15BD401053DAEC4A0A490D4C%40AdobeOrg=1994364360%7CMCIDTS%7C18680%7CvVersion%7C3.4.0; DOMAIN=www.etihad.com");

	web_add_cookie("check=true; DOMAIN=www.etihad.com");

	web_add_cookie("mbox=session#acfdcd4c076546849d13a0a9b5f1f504#1613934660; DOMAIN=www.etihad.com");

	web_add_cookie("Etihad.com_AEM_SiteEdition=; DOMAIN=www.etihad.com");

	web_add_cookie("Etihad.com_AEM_SiteEdition=en-us; DOMAIN=www.etihad.com");

	web_add_header("Sec-Fetch-User", 
		"?1");

	web_add_header("Upgrade-Insecure-Requests", 
		"1");

	web_add_auto_header("sec-ch-ua", 
		"\"Chromium\";v=\"88\", \"Google Chrome\";v=\"88\", \";Not A Brand\";v=\"99\"");

	web_add_auto_header("sec-ch-ua-mobile", 
		"?0");

	web_add_cookie("_sctr=1|1613858400000; DOMAIN=www.etihad.com");

	web_add_cookie("AAMC_etihadairways_0=REGION%7C6; DOMAIN=www.etihad.com");

	web_add_cookie("mf_recordingRules=W1siYmFzZSIsIioiLDI1LGZhbHNlXSxbInN0YXJ0c1dpdGgiLCIvZHgiLDEwMF1d; DOMAIN=www.etihad.com");

	web_add_cookie("mf_226ff167-994e-4acb-85f6-1678320b3aad=|.46435224.1613932822942|1613932822960||0|||0|17.37|58.53512; DOMAIN=www.etihad.com");

	web_add_cookie("bd4ti=Sl68_InJJTao.1613932824190; DOMAIN=www.etihad.com");

	web_add_cookie("s_ptc=%5B%5BB%5D%5D; DOMAIN=www.etihad.com");

	web_add_cookie("s_sq="
		"etihaddotcomprod%3D%2526c.%2526a.%2526activitymap.%2526page%253Dbook%2526link%253DType%252520of%252520Journey%252520Return%252520One%252520way%252520Multi-city%252520Flying%252520From%2525200%252520selections%252520Toggle%252520origin%252520destination%252520Flying%252520to%2525200%252520selections%252520Outbound%252520Ret%2526region%253Dbook%2526pageIDType%253D1%2526.activitymap%2526.a%2526.c%2526pid%253Dbook%2526pidt%253D1%2526oid%253Dfunctionlt%252528%252529%25257B%25257D%2526oidt%253D2%2526ot%"
		"253DDIV; DOMAIN=www.etihad.com");

	web_add_cookie("s_ppv=book%2C25%2C15%2C1221%2C1320%2C725%2Cundefined%2Cundefined%2C1%2CP; DOMAIN=www.etihad.com");

	web_add_cookie("s_sq=etihaddotcomprod%3D%2526c.%2526a.%2526activitymap.%2526page%253Dbook%2526link%253DAbu%252520Dhabi%252520AUH%252520Abu%252520Dhabi%252520Airport%252520United%252520Arab%252520Emirates%2526region%253Drbt-menu-item-5%2526pageIDType%253D1%2526.activitymap%2526.a%2526.c%2526pid%253Dbook%2526pidt%253D1%2526oid%253Dhttps%25253A%25252F%25252Fwww.etihad.com%25252Fen-us%25252Fbook%252523%2526ot%253DA; DOMAIN=www.etihad.com");

	web_add_cookie("s_sq=etihaddotcomprod%3D%2526c.%2526a.%2526activitymap.%2526page%253Dbook%2526link%253DBengaluru%252520BLR%252520Kempegowda%252520Airport%252520India%2526region%253Drbt-menu-item-0%2526pageIDType%253D1%2526.activitymap%2526.a%2526.c%2526pid%253Dbook%2526pidt%253D1%2526oid%253Dhttps%25253A%25252F%25252Fwww.etihad.com%25252Fen-us%25252Fbook%252523%2526ot%253DA; DOMAIN=www.etihad.com");

	web_url("book", 
		"URL=https://www.etihad.com/en-us/book", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=", 
		"Snapshot=t67.inf", 
		"Mode=HTML", 
		EXTRARES, 
		"Url=https://assets.adobedtm.com/8aea536f4a27/88ed4f88d8f6/b952b1e8a447/EXc507feef13be45718d3f3a18fa1caf17-libraryCode_source.min.js", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/logo/header/header-text-image-web.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/logo/header/header-text-image-scroll.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/Common/header-search-icon.svg", ENDITEM, 
		"Url=https://www.google-analytics.com/analytics.js", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://www.googletagmanager.com/gtm.js?id=GTM-T8JNC39", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=../etc/designs/eag/eag-commons/images/toggle-btn-desktop-disabled.svg", "Referer=https://www.etihad.com/etc/designs/eag/etihadairways/etihadcom/clientlibs-en-homepage.css", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/Common/passenger-decrement-icon.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/Common/passenger-type-adult.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/Common/passenger-increment-icon.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/Common/passenger-type-child.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/Common/passenger-type-infant.svg", ENDITEM, 
		"Url=../etc/designs/eag/eag-commons/images/Global/Common/new_window_icon.svg", "Referer=https://www.etihad.com/etc/designs/eag/etihadairways/etihadcom/clientlibs-en-homepage.css", ENDITEM, 
		"Url=../etc/designs/eag/eag-commons/fonts/english/EtihadAltisHeadline-Regular.woff", "Referer=https://www.etihad.com/etc/designs/eag/etihadairways/etihadcom/clientlibs-en-homepage.css", ENDITEM, 
		"Url=../etc/designs/eag/eag-commons/fonts/english/etihadaltis-book_v3-webfont.woff", "Referer=https://www.etihad.com/etc/designs/eag/etihadairways/etihadcom/clientlibs-en-homepage.css", ENDITEM, 
		"Url=../etc/designs/eag/eag-commons/fonts/english/EtihadAltis-Bold_V3.woff", "Referer=https://www.etihad.com/etc/designs/eag/etihadairways/etihadcom/clientlibs-en-homepage.css", ENDITEM, 
		"Url=../etc/designs/eag/eag-commons/images/warning-message-icon-white.svg", "Referer=https://www.etihad.com/etc/designs/eag/etihadairways/etihadcom/clientlibs-en-homepage.css", ENDITEM, 
		"Url=../etc/designs/eag/eag-commons/images/dropdown_notch.svg", "Referer=https://www.etihad.com/etc/designs/eag/etihadairways/etihadcom/clientlibs-en-homepage.css", ENDITEM, 
		"Url=../etc/designs/eag/eag-commons/images/close-btn.svg", "Referer=https://www.etihad.com/etc/designs/eag/etihadairways/etihadcom/clientlibs-en-homepage.css", ENDITEM, 
		"Url=../etc/designs/eag/eag-commons/images/toggle-btn-desktop.svg", "Referer=https://www.etihad.com/etc/designs/eag/etihadairways/etihadcom/clientlibs-en-homepage.css", ENDITEM, 
		"Url=../etc/designs/eag/eag-commons/images/cal-arrow-left.svg", "Referer=https://www.etihad.com/etc/designs/eag/etihadairways/etihadcom/clientlibs-en-homepage.css", ENDITEM, 
		"Url=../etc/designs/eag/eag-commons/images/cal-arrow-right.svg", "Referer=https://www.etihad.com/etc/designs/eag/etihadairways/etihadcom/clientlibs-en-homepage.css", ENDITEM, 
		LAST);

	web_add_auto_header("Sec-Fetch-Dest", 
		"empty");

	web_add_auto_header("Sec-Fetch-Mode", 
		"cors");

	web_add_auto_header("Sec-Fetch-Site", 
		"same-origin");

	web_url("countryCode", 
		"URL=https://www.etihad.com/edge-services/countryCode", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://www.etihad.com/en-us/book", 
		"Snapshot=t68.inf", 
		"Mode=HTML", 
		LAST);

	web_url("origin.json", 
		"URL=https://www.etihad.com/edge-services/ns/all-ond-auto/v210/en/origin.json", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://www.etihad.com/en-us/book", 
		"Snapshot=t69.inf", 
		"Mode=HTML", 
		LAST);

	web_add_cookie("bm_sv=BF754C0F839303B60F05AA7DD5C1BA66~vJQguBUGVJEkio3vFREar91A6/LWWLyGm7QUtNvloHIt8lgMvuha45Sib63M8nEDT8gqsZuiJeDd/eOAv2ODuLAgeqhqfRbRyLs9U9WUdnwfi8zirKgyx6r0x3K6xCHIR/Eff1LTxiUDNoQPjvHx7MwWirRUeA4qg44AsEgWOXA=; DOMAIN=www.etihad.com");

	web_add_auto_header("Sec-Fetch-Mode", 
		"no-cors");

	web_add_auto_header("Sec-Fetch-Dest", 
		"image");

	web_add_cookie("scSiteEdition=en-us; DOMAIN=www.etihad.com");

	web_add_cookie("_gcl_au=1.1.706136819.1613932803; DOMAIN=www.etihad.com");

	web_add_cookie("_gat_UA-36260312-1=1; DOMAIN=www.etihad.com");

	web_add_cookie("AMCVS_15BD401053DAEC4A0A490D4C%40AdobeOrg=1; DOMAIN=www.etihad.com");

	web_add_cookie("mbox=session#acfdcd4c076546849d13a0a9b5f1f504#1613934665|PC#acfdcd4c076546849d13a0a9b5f1f504.37_0#1677177605; DOMAIN=www.etihad.com");

	web_add_cookie("AMCV_15BD401053DAEC4A0A490D4C%40AdobeOrg=1994364360%7CMCIDTS%7C18680%7CMCMID%7C26185866700827066552622528666916780474%7CMCAID%7CNONE%7CMCOPTOUT-1613940003s%7CNONE%7CMCAAMLH-1614537605%7C6%7CMCAAMB-1614537605%7Cj8Odv6LonN4r3an7LhD3WZrU1bUpAkFkkiY1ncBR96t2PTI%7CvVersion%7C3.4.0; DOMAIN=www.etihad.com");

	web_add_cookie("_ga_G21M2QMYH2=GS1.1.1613932802.1.0.1613932802.0; DOMAIN=www.etihad.com");

	web_add_cookie("_ga=GA1.1.686669418.1613932802; DOMAIN=www.etihad.com");

	web_add_cookie("_uetsid=38efc2d0747411eb824c037565713347; DOMAIN=www.etihad.com");

	web_add_cookie("_uetvid=38f49e70747411eb84608354d038f035; DOMAIN=www.etihad.com");

	web_add_cookie("AMCV_15BD401053DAEC4A0A490D4C%40AdobeOrg=1994364360%7CMCIDTS%7C18680%7CMCMID%7C26185866700827066552622528666916780474%7CMCAID%7CNONE%7CMCOPTOUT-1613940003s%7CNONE%7CMCAAMLH-1614537605%7C6%7CMCAAMB-1614537605%7Cj8Odv6LonN4r3an7LhD3WZrU1bUpAkFkkiY1ncBR96t2PTI%7CMCSYNCSOP%7C411-18687%7CvVersion%7C3.4.0; DOMAIN=www.etihad.com");

	web_add_cookie("stc115172=tsa:1613932810286.445323167.50012636.5997666628335889.:20210221191010|env:1%7C20210324184010%7C20210221191010%7C1%7C1047195:20220221184010|uid:1613932810285.1674410045.7457108.115172.428744288.:20220221184010|srchist:1047195%3A1%3A20210324184010:20220221184010; DOMAIN=www.etihad.com");

	web_add_cookie("s_pageName_cookie=book; DOMAIN=www.etihad.com");

	web_add_cookie("s_srv=etihad-adobe-aem-hosting; DOMAIN=www.etihad.com");

	web_add_cookie("s_ppvl=%5B%5BB%5D%5D; DOMAIN=www.etihad.com");

	web_add_cookie("s_ppv=book%2C15%2C15%2C725%2C1320%2C725%2Cundefined%2Cundefined%2C1%2CP; DOMAIN=www.etihad.com");

	web_add_cookie("s_cc=true; DOMAIN=www.etihad.com");

	web_add_cookie("s_visit=1; DOMAIN=www.etihad.com");

	web_add_cookie("s_ppv=book%2C26%2C15%2C1258%2C1320%2C725%2Cundefined%2Cundefined%2C1%2CP; DOMAIN=www.etihad.com");

	web_add_cookie("s_sq="
		"etihaddotcomprod%3D%2526c.%2526a.%2526activitymap.%2526page%253Dbook%2526link%253DType%252520of%252520Journey%252520Return%252520One%252520way%252520Multi-city%252520Flying%252520From%252520Clear%2525201%252520selection%252520Toggle%252520origin%252520destination%252520Flying%252520to%252520Clear%2525201%252520selection%252520Ou%2526region%253Dbook%2526pageIDType%253D1%2526.activitymap%2526.a%2526.c%2526pid%253Dbook%2526pidt%253D1%2526oid%253Dfunctionlt%252528%252529%25257B%25257D%2526oidt%253D2%2"
		"526ot%253DDIV; DOMAIN=www.etihad.com");

	web_url("book_2", 
		"URL=https://www.etihad.com/en-us/book", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=https://www.etihad.com/en-us/book", 
		"Snapshot=t70.inf", 
		"Mode=HTML", 
		EXTRARES, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/Common/social-linkedin-icon.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/Common/social-facebook-icon.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/Common/social-Instagram-icon.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/Common/social-twitter-icon.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/Common/globe-icon.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/Common/close-btn.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/Common/social-youtube-icon.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/icons/top-nav/ancillary-seat.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/icons/top-nav/manage-your-booking.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/icons/top-nav/check-in-area.svg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/fav-icon/favicon.png", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/products/ancillaries/etihad-chauffer/etihad-chauffeur-aircraft-1280x1280.jpeg", ENDITEM, 
		"Url=../content/dam/eag/etihadairways/etihadcom/Global/products/fleet/boeing-airplane-787-flying-in-the-sky.jpg", ENDITEM, 
		LAST);

	web_add_auto_header("Sec-Fetch-Site", 
		"cross-site");

	web_add_auto_header("Sec-Fetch-Mode", 
		"cors");

	web_add_auto_header("Sec-Fetch-Dest", 
		"empty");

	web_add_auto_header("Origin", 
		"https://www.etihad.com");

	web_custom_request("id", 
		"URL=https://dpm.demdex.net/id?d_visid_ver=3.4.0&d_fieldgroup=MC&d_rtbd=json&d_ver=2&d_verify=1&d_orgid=15BD401053DAEC4A0A490D4C%40AdobeOrg&d_nsid=0&ts=1613932798751", 
		"Method=GET", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://www.etihad.com/", 
		"Snapshot=t71.inf", 
		"Mode=HTML", 
		"EncType=application/x-www-form-urlencoded", 
		EXTRARES, 
		"Url=https://assets.adobedtm.com/8aea536f4a27/88ed4f88d8f6/b952b1e8a447/RC1c8d73cc626542449e406ad98ea1f6ac-source.min.js", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://assets.adobedtm.com/8aea536f4a27/88ed4f88d8f6/b952b1e8a447/RC193adf3997624cfdb67c2e73b971e795-source.min.js", "Referer=https://www.etihad.com/", ENDITEM, 
		LAST);

	web_add_auto_header("Sec-Fetch-Site", 
		"same-origin");

	web_custom_request("c6ed2e6ff94ti174ef6d9b189a16c414b", 
		"URL=https://www.etihad.com/staticweb/c6ed2e6ff94ti174ef6d9b189a16c414b", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://www.etihad.com/en-us/book", 
		"Snapshot=t72.inf", 
		"Mode=HTML", 
		"EncType=text/plain;charset=UTF-8", 
		"Body={\"sensor_data\":\"7a74G7m23Vrp0o5c9135291.67-1,2,-94,-100,Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36,uaend,12147,20030107,en-US,Gecko,3,0,0,0,397104,2801154,1440,860,1440,900,1320,725,1336,,cpen:0,i1:0,dm:0,cwen:0,non:1,opc:0,fc:0,sc:0,wrc:1,isc:0,vib:1,bat:1,x11:0,x12:1,8329,0.10240306151,806966400576,0,loc:-1,2,-94,-101,do_en,dm_en,t_en-1,2,-94,-105,0,0,0,0,1583,-1,0;-1,-1,0,0,-1,-1,0;0,0,0,0,2121,-1,0;-1,-1,0,0,-1,-1"
		",0;0,0,0,0,2751,2751,1;0,0,0,0,2783,2783,0;-1,2,-94,-102,0,0,0,0,1583,-1,0;-1,-1,0,0,-1,-1,0;0,0,0,0,2121,-1,0;-1,-1,0,0,-1,-1,0;0,0,0,0,2751,2751,1;0,0,0,0,2783,2783,0;-1,2,-94,-108,-1,2,-94,-110,-1,2,-94,-117,-1,2,-94,-111,-1,2,-94,-109,-1,2,-94,-114,-1,2,-94,-103,-1,2,-94,-112,https://www.etihad.com/en-us/book-1,2,-94,-115,1,32,32,0,0,0,0,4,0,1613932801152,-999999,17265,0,0,2877,0,0,12,0,0,47ACA25003F1D5F4A04DC7C4F295531C~-1~YAAQL1BzaL/L87V3AQAAuMrkxQUMThvqnLIgmuL+gsU6h+"
		"Nf48vTGcswi5MAJugFOgHwy63qGBHASRcDVjhQSMS4FoHA7UItl7oVQIZulBUunlXXK1nkUA641L0A7I7XvH/JWS5VFy5CwnfhKXU2Jek2pOI9u8SVzdNfCJVw2RDpzgtzdphKo+UTSdhMRm8dXvJobb515Z+hFZcIHnCHpO9EmLtCZiHoYb3rjWJfndHauGLvYdsJfk5lkRuxhlAPn3MPiVGcr5GYdXD0LUO/Qn1ku+X7j5TEp46/g3Kfu2r5bl2lq/cT5gpa~-1~-1~-1,29355,-1,-1,30261693,PiZtE,62341,79-1,2,-94,-106,0,0-1,2,-94,-119,-1-1,2,-94,-122,0,0,0,0,1,0,0-1,2,-94,-123,-1,2,-94,-124,-1,2,-94,-126,-1,2,-94,-127,8-1,2,-94,-70,-1-1,2,-94,-80,94-1,2,-94,-116,42017340-1,2,-94,-118,86234-1,"
		"2,-94,-129,-1,2,-94,-121,;17;-1;0\"}", 
		LAST);

	web_add_cookie("_ga=GA1.2.686669418.1613932802; DOMAIN=www.etihad.com");

	web_add_cookie("_gid=GA1.2.1288094177.1613932802; DOMAIN=www.etihad.com");

	web_add_cookie("_abck=47ACA25003F1D5F4A04DC7C4F295531C~-1~YAAQL1BzaL/L87V3AQAAuMrkxQUMThvqnLIgmuL+gsU6h+Nf48vTGcswi5MAJugFOgHwy63qGBHASRcDVjhQSMS4FoHA7UItl7oVQIZulBUunlXXK1nkUA641L0A7I7XvH/JWS5VFy5CwnfhKXU2Jek2pOI9u8SVzdNfCJVw2RDpzgtzdphKo+UTSdhMRm8dXvJobb515Z+hFZcIHnCHpO9EmLtCZiHoYb3rjWJfndHauGLvYdsJfk5lkRuxhlAPn3MPiVGcr5GYdXD0LUO/Qn1ku+X7j5TEp46/g3Kfu2r5bl2lq/cT5gpa~-1~-1~-1; DOMAIN=smetrics.etihad.com");

	web_add_cookie("AMCV_15BD401053DAEC4A0A490D4C%40AdobeOrg=1994364360%7CMCIDTS%7C18680%7CvVersion%7C3.4.0; DOMAIN=smetrics.etihad.com");

	web_add_cookie("ak_bmsc=A9F9127680F343DD461B5FC9E5463D586873502F241C000029A9326087F17029~plj7sIZ3p334zOe1MHxum8ykVpNGxDN3CbUR6txoy2zIHshdDLfIiEhO4YCwh5/zK8ye33oJ7lyF/lGKyRy1bQuPLRThq/5vESTBOYP1r/Gvar8gECqXxxr3WEh8k/PE1JMnpMTg7KMApB+SgCEcRJqHH4J+B/RAOgwOTn9E9wlGJHx8sX/uN/I30fJHHXpOkny+SNCnHdwxB++0irDS4j4JJ6R/N1Xe+OZeyAWCK3bi/xOX8Cht6rQQpYyIm6wIFmujNI/IuWifRkayCCA/CUoFvpbe44TGkRQTRF+PnTsH/6qbYsQLn6FhfEUkVdWLluks1I5vkvS1nEETXnBIhzpg==; DOMAIN=smetrics.etihad.com");

	web_add_cookie("check=true; DOMAIN=smetrics.etihad.com");

	web_add_cookie("mbox=session#acfdcd4c076546849d13a0a9b5f1f504#1613934660; DOMAIN=smetrics.etihad.com");

	web_add_cookie("Etihad.com_AEM_SiteEdition=en-us; DOMAIN=smetrics.etihad.com");

	web_custom_request("pixel_7ce4ad12", 
		"URL=https://www.etihad.com/akam/11/pixel_7ce4ad12", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=https://www.etihad.com/en-us/book", 
		"Snapshot=t73.inf", 
		"Mode=HTML", 
		"Body=ap=true&bt=%7B%22charging%22%3Atrue%2C%22chargingTime%22%3A0%2C%22dischargingTime%22%3A%22Infinity%22%2C%22level%22%3A1%2C%22onchargingchange%22%3Anull%2C%22onchargingtimechange%22%3Anull%2C%22ondischargingtimechange%22%3Anull%2C%22onlevelchange%22%3Anull%7D&fonts=7%2C63&fh=fd93e714c2d4dd2d57da0040151ad1301d069e1a&timing="
		"%7B%221%22%3A241%2C%222%22%3A782%2C%22profile%22%3A%7B%22bp%22%3A0%2C%22sr%22%3A18%2C%22dp%22%3A1%2C%22lt%22%3A0%2C%22ps%22%3A1%2C%22cv%22%3A174%2C%22fp%22%3A0%2C%22sp%22%3A1%2C%22br%22%3A0%2C%22ieps%22%3A1%2C%22av%22%3A0%2C%22z1%22%3A36%2C%22jsv%22%3A1%2C%22nav%22%3A2%2C%22nap%22%3A3%2C%22crc%22%3A1%2C%22z2%22%3A2%2C%22fonts%22%3A341%7D%2C%22main%22%3A3765%2C%22compute%22%3A241%2C%22send%22%3A1123%7D&bp=1038350511%2C-1979380391%2C1738406762%2C749224105&sr="
		"%7B%22inner%22%3A%5B1320%2C725%5D%2C%22outer%22%3A%5B1336%2C813%5D%2C%22screen%22%3A%5B30%2C30%5D%2C%22pageOffset%22%3A%5B0%2C0%5D%2C%22avail%22%3A%5B1440%2C860%5D%2C%22size%22%3A%5B1440%2C900%5D%2C%22client%22%3A%5B1303%2C4837%5D%2C%22colorDepth%22%3A24%2C%22pixelDepth%22%3A24%7D&dp="
		"%7B%22XDomainRequest%22%3A0%2C%22createPopup%22%3A0%2C%22removeEventListener%22%3A1%2C%22globalStorage%22%3A0%2C%22openDatabase%22%3A1%2C%22indexedDB%22%3A1%2C%22attachEvent%22%3A0%2C%22ActiveXObject%22%3A0%2C%22dispatchEvent%22%3A1%2C%22addBehavior%22%3A0%2C%22addEventListener%22%3A1%2C%22detachEvent%22%3A0%2C%22fireEvent%22%3A0%2C%22MutationObserver%22%3A1%2C%22HTMLMenuItemElement%22%3A0%2C%22Int8Array%22%3A1%2C%22postMessage%22%3A1%2C%22querySelector%22%3A1%2C%22getElementsByClassName%22%3A1%2C"
		"%22images%22%3A1%2C%22compatMode%22%3A%22CSS1Compat%22%2C%22documentMode%22%3A0%2C%22all%22%3A1%2C%22now%22%3A1%2C%22contextMenu%22%3A0%7D&lt=1613932801207%2B2&ps=true%2Ctrue&cv=0c746aa6743036d3834aeeaf1a96693b7465b169&fp=false&sp=false&br=Chrome&ieps=false&av=false&z=%7B%22a%22%3A2095361639%2C%22b%22%3A1%2C%22c%22%3A0%7D&zh=&jsv=1.7&nav=%7B%22userAgent%22%3A%22Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20Win64%3B%20x64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)"
		"%20Chrome%2F88.0.4324.182%20Safari%2F537.36%22%2C%22appName%22%3A%22Netscape%22%2C%22appCodeName%22%3A%22Mozilla%22%2C%22appVersion%22%3A%225.0%20(Windows%20NT%2010.0%3B%20Win64%3B%20x64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)"
		"%20Chrome%2F88.0.4324.182%20Safari%2F537.36%22%2C%22appMinorVersion%22%3A0%2C%22product%22%3A%22Gecko%22%2C%22productSub%22%3A%2220030107%22%2C%22vendor%22%3A%22Google%20Inc.%22%2C%22vendorSub%22%3A%22%22%2C%22buildID%22%3A0%2C%22platform%22%3A%22Win32%22%2C%22oscpu%22%3A0%2C%22hardwareConcurrency%22%3A2%2C%22language%22%3A%22en-US%22%2C%22languages%22%3A%5B%22en-US%22%5D%2C%22systemLanguage%22%3A0%2C%22userLanguage%22%3A0%2C%22doNotTrack%22%3Anull%2C%22msDoNotTrack%22%3A0%2C%22cookieEnabled%22%3A"
		"true%2C%22geolocation%22%3A1%2C%22vibrate%22%3A1%2C%22maxTouchPoints%22%3A0%2C%22webdriver%22%3A0%2C%22plugins%22%3A%5B%22Chrome%20PDF%20Plugin%22%2C%22Chrome%20PDF%20Viewer%22%2C%22Native%20Client%22%5D%7D&crc="
		"%7B%22window.chrome%22%3A%7B%22app%22%3A%7B%22isInstalled%22%3Afalse%2C%22InstallState%22%3A%7B%22DISABLED%22%3A%22disabled%22%2C%22INSTALLED%22%3A%22installed%22%2C%22NOT_INSTALLED%22%3A%22not_installed%22%7D%2C%22RunningState%22%3A%7B%22CANNOT_RUN%22%3A%22cannot_run%22%2C%22READY_TO_RUN%22%3A%22ready_to_run%22%2C%22RUNNING%22%3A%22running%22%7D%7D%2C%22runtime%22%3A%7B%22OnInstalledReason%22%3A%7B%22CHROME_UPDATE%22%3A%22chrome_update%22%2C%22INSTALL%22%3A%22install%22%2C%22SHARED_MODULE_UPDATE%"
		"22%3A%22shared_module_update%22%2C%22UPDATE%22%3A%22update%22%7D%2C%22OnRestartRequiredReason%22%3A%7B%22APP_UPDATE%22%3A%22app_update%22%2C%22OS_UPDATE%22%3A%22os_update%22%2C%22PERIODIC%22%3A%22periodic%22%7D%2C%22PlatformArch%22%3A%7B%22ARM%22%3A%22arm%22%2C%22ARM64%22%3A%22arm64%22%2C%22MIPS%22%3A%22mips%22%2C%22MIPS64%22%3A%22mips64%22%2C%22X86_32%22%3A%22x86-32%22%2C%22X86_64%22%3A%22x86-64%22%7D%2C%22PlatformNaclArch%22%3A%7B%22ARM%22%3A%22arm%22%2C%22MIPS%22%3A%22mips%22%2C%22MIPS64%22%3A%"
		"22mips64%22%2C%22X86_32%22%3A%22x86-32%22%2C%22X86_64%22%3A%22x86-64%22%7D%2C%22PlatformOs%22%3A%7B%22ANDROID%22%3A%22android%22%2C%22CROS%22%3A%22cros%22%2C%22LINUX%22%3A%22linux%22%2C%22MAC%22%3A%22mac%22%2C%22OPENBSD%22%3A%22openbsd%22%2C%22WIN%22%3A%22win%22%7D%2C%22RequestUpdateCheckStatus%22%3A%7B%22NO_UPDATE%22%3A%22no_update%22%2C%22THROTTLED%22%3A%22throttled%22%2C%22UPDATE_AVAILABLE%22%3A%22update_available%22%7D%7D%7D%7D&t=ed57f77bc0bec3f8b6e5cc343447719e421afd8f&u="
		"15d0a2b3a43c5004b9bd98f93c3a0b85&fc=false", 
		EXTRARES, 
		"Url=https://content-autofill.googleapis.com/v1/pages/ChRDaHJvbWUvODguMC40MzI0LjE4MhIzCSxQakX-OlYOEgUN8rj3OhIFDZFhlU4SBQ0o7i3wEgUNkWGVThIFDSVNuHgSBQ2mBLaP?alt=proto", "Referer=", ENDITEM, 
		"Url=https://assets.adobedtm.com/extensions/EPb3826f174b534354aaa5a9e9f1dab55d/AppMeasurement_Module_AudienceManagement.min.js", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://smetrics.etihad.com/id?d_visid_ver=3.4.0&d_fieldgroup=MC&mcorgid=15BD401053DAEC4A0A490D4C%40AdobeOrg&ts=1613932801533", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://www.googletagmanager.com/gtag/js?id=G-G21M2QMYH2&l=dataLayer&cx=c", "Referer=https://www.etihad.com/", ENDITEM, 
		LAST);

	web_custom_request("c6ed2e6ff94ti174ef6d9b189a16c414b_2", 
		"URL=https://www.etihad.com/staticweb/c6ed2e6ff94ti174ef6d9b189a16c414b", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://www.etihad.com/en-us/book", 
		"Snapshot=t74.inf", 
		"Mode=HTML", 
		"EncType=text/plain;charset=UTF-8", 
		"Body={\"sensor_data\":\"7a74G7m23Vrp0o5c9135291.67-1,2,-94,-100,Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36,uaend,12147,20030107,en-US,Gecko,3,0,0,0,397104,2801154,1440,860,1440,900,1320,725,1336,,cpen:0,i1:0,dm:0,cwen:0,non:1,opc:0,fc:0,sc:0,wrc:1,isc:0,vib:1,bat:1,x11:0,x12:1,8329,0.06721616033,806966400576,0,loc:-1,2,-94,-101,do_en,dm_en,t_en-1,2,-94,-105,0,0,0,0,1583,-1,0;-1,-1,0,0,-1,-1,0;0,0,0,0,2121,-1,0;-1,-1,0,0,-1,-1"
		",0;0,0,0,0,2751,2751,1;0,0,0,0,2783,2783,0;-1,2,-94,-102,0,0,0,0,1583,-1,0;-1,-1,0,0,-1,-1,0;0,0,0,0,2121,-1,0;-1,-1,0,0,-1,-1,0;0,0,0,0,2751,2751,1;0,0,0,0,2783,2783,0;-1,2,-94,-108,-1,2,-94,-110,-1,2,-94,-117,-1,2,-94,-111,-1,2,-94,-109,-1,2,-94,-114,-1,2,-94,-103,-1,2,-94,-112,https://www.etihad.com/en-us/book-1,2,-94,-115,1,32,32,0,0,0,0,1387,0,1613932801152,17,17265,0,0,2877,0,0,1389,0,0,47ACA25003F1D5F4A04DC7C4F295531C~-1~YAAQL1BzaMbL87V3AQAAMNvkxQW9xpRMpWGVTxnrUv5/"
		"NtyRSOxFiNe6ZSfOxMmSBccPufxGIoFOPI7ac9imC249SUnC5ZVYpUu6JD+PISV2kkpfBdu3ysVckiko/UmHhLcjiHE2QobbpZOkviIkf97O8VaJ7pABAXwMDtg0N3cZYeA4I+jiD1Q7BxyPfCKJSU9YF/XaKgJCqnad9cya7FRKQidPtOLCoVg/gMttuDnOWUojVOxky9O11zanty8mzc+u+7wjWFdJbJRSkNAe3oikUbawM0y7COCkMcklr+qtEiH0BtBdYAQlXgYSUX3GDUAzG9pRz38=~-1~||1-UTAPzAFFlv-1-10-1000-2||~-1,33360,251,202730061,30261693,PiZtE,40980,58-1,2,-94,-106,9,1-1,2,-94,-119,72,135,133,134,110,103,72,67,69,99,76,952,1069,593,-1,2,-94,-122,0,0,0,0,1,0,0-1,2,-94,-123,-1,2,-94,"
		"-124,-1,2,-94,-126,-1,2,-94,-127,11321144241322243122-1,2,-94,-70,-1752250632;-915298718;dis;,7,8;true;true;true;-120;true;24;24;true;false;-1-1,2,-94,-80,5624-1,2,-94,-116,42017340-1,2,-94,-118,94016-1,2,-94,-129,ddf68f2157d3c0edf2744f0b57b5e4c63840c2c94b8cc853141f293336e1e305,1,a712c19fde04cde08d21754c81e951066896404f0eac6cbfd5255e434d879986,,,,0-1,2,-94,-121,;241;33;1\"}", 
		LAST);

	web_add_auto_header("Sec-Fetch-Site", 
		"cross-site");

	web_custom_request("collect", 
		"URL=https://www.google-analytics.com/j/collect?v=1&_v=j88&a=1699017474&t=pageview&_s=1&dl=https%3A%2F%2Fwww.etihad.com%2Fen-us%2Fbook&ul=en-us&de=UTF-8&dt=Book%20your%20flights%20with%20Etihad%20Airways%20USA&sd=24-bit&sr=1440x900&vp=1303x725&je=0&_u=aEDAAUABAAAAAC~&jid=1260145807&gjid=498338028&cid=686669418.1613932802&tid=UA-36260312-1&_gid=1288094177.1613932802&_r=1&gtm=2wg2a1T8JNC39&cd11=en-us&cd13=1994364360&cd15=anonymous&cm1=0&z=1062356741", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=text/plain", 
		"Referer=https://www.etihad.com/", 
		"Snapshot=t75.inf", 
		"Mode=HTML", 
		"EncType=text/plain", 
		LAST);

	web_url("json_2", 
		"URL=https://etihadairways.tt.omtrdc.net/m2/etihadairways/mbox/json?mbox=target-global-mbox&mboxSession=acfdcd4c076546849d13a0a9b5f1f504&mboxPC=&mboxPage=22eaa1d994ba475d8c03fc0debe75134&mboxRid=84e1715e4ee748099285224bc2ef45bd&mboxVersion=1.7.0&mboxCount=1&mboxTime=1613939999072&mboxHost=www.etihad.com&mboxURL=https%3A%2F%2Fwww.etihad.com%2Fen-us%2Fbook&mboxReferrer=&mboxXDomain=enabled&browserHeight=725&browserWidth=1320&browserTimeOffset=120&screenHeight=900&screenWidth=1440&colorDepth=24&"
		"devicePixelRatio=1&screenOrientation=landscape&webGLRenderer=Google%20SwiftShader&flowType=&language=en&products=&tntaction=load&dxPageName=&at_property=864a2d44-7fb8-1f02-f8d0-d680f344979c&journeyType=&siteEdition=en-us&FlightADTPax=0&FlightCHDPax=0&FlightINFPax=0&dx-searchOND=&oneappsource=&redemptionflow=&bookingCurrency=&Analyticspagename=&Inbound_days_left=&dx-searchdnrDates=&inBoundCabinClass=&Outbound_days_left=&outBoundCabinClass=&mboxMCSDID=6A3F59C891286C9C-67A9CA130B15A327&vst.trk="
		"metrics.etihad.com&vst.trks=smetrics.etihad.com", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://www.etihad.com/", 
		"Snapshot=t76.inf", 
		"Mode=HTML", 
		EXTRARES, 
		"Url=https://snap.licdn.com/li.lms-analytics/insight.min.js", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://static.ads-twitter.com/uwt.js", "Referer=https://www.etihad.com/", ENDITEM, 
		LAST);

	web_custom_request("id_2", 
		"URL=https://dpm.demdex.net/id?d_visid_ver=3.4.0&d_fieldgroup=AAM&d_rtbd=json&d_ver=2&d_orgid=15BD401053DAEC4A0A490D4C%40AdobeOrg&d_nsid=0&d_mid=26185866700827066552622528666916780474&ts=1613932803869", 
		"Method=GET", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://www.etihad.com/", 
		"Snapshot=t77.inf", 
		"Mode=HTML", 
		"EncType=application/x-www-form-urlencoded", 
		EXTRARES, 
		"Url=https://connect.facebook.net/en_US/fbevents.js", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://bat.bing.com/bat.js", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://sc-static.net/scevent.min.js", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://intljs.rmtag.com/115172.ct.js", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://t.cfjump.com/tag/30860", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://www.dwin1.com/5326.js", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://www.dwin1.com/7983.js", "Referer=https://www.etihad.com/", ENDITEM, 
		LAST);

	web_revert_auto_header("Origin");

	web_add_auto_header("Sec-Fetch-Mode", 
		"navigate");

	web_add_auto_header("Sec-Fetch-Dest", 
		"iframe");

	web_add_header("Upgrade-Insecure-Requests", 
		"1");

	web_url("dc_pre=CJ6o77DQ--4CFZLj7Qod-gwFOw;src=4338867;type=Count-;cat=Etiha-;ord=4336956492503;gtm=2wg2a1;auiddc=706136819.1613932803;u57=book;u59=1994364360;u40=en;u61=us;u63=Desktop;u64=www.etihad.com;~oref=https%3A%2F%2Fwww.etihad.com%2Fen-us%2Fbook", 
		"URL=https://adservice.google.com/ddm/fls/i/dc_pre=CJ6o77DQ--4CFZLj7Qod-gwFOw;src=4338867;type=Count-;cat=Etiha-;ord=4336956492503;gtm=2wg2a1;auiddc=706136819.1613932803;u57=book;u59=1994364360;u40=en;u61=us;u63=Desktop;u64=www.etihad.com;~oref=https%3A%2F%2Fwww.etihad.com%2Fen-us%2Fbook", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=https://4338867.fls.doubleclick.net/", 
		"Snapshot=t78.inf", 
		"Mode=HTML", 
		LAST);

	web_add_auto_header("Sec-Fetch-Mode", 
		"cors");

	web_add_auto_header("Sec-Fetch-Dest", 
		"empty");

	web_add_header("Origin", 
		"https://www.etihad.com");

	web_custom_request("collect_2", 
		"URL=https://stats.g.doubleclick.net/j/collect?t=dc&aip=1&_r=3&v=1&_v=j88&tid=UA-36260312-1&cid=686669418.1613932802&jid=1260145807&gjid=498338028&_gid=1288094177.1613932802&_u=aEDAAUAAAAAAAC~&z=1245816121", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=text/plain", 
		"Referer=https://www.etihad.com/", 
		"Snapshot=t79.inf", 
		"Mode=HTML", 
		"EncType=text/plain", 
		LAST);

	web_add_auto_header("Sec-Fetch-Mode", 
		"no-cors");

	web_add_auto_header("Sec-Fetch-Dest", 
		"image");

	web_url("0", 
		"URL=https://bat.bing.com/action/0?ti=4017239&Ver=2&mid=9efc6fd9-1b65-4ebc-9e62-ff4af90f9b14&sid=38efc2d0747411eb824c037565713347&vid=38f49e70747411eb84608354d038f035&vids=1&pi=1200101525&lg=en-US&sw=1440&sh=900&sc=24&tl=Book%20your%20flights%20with%20Etihad%20Airways%20USA&p=https%3A%2F%2Fwww.etihad.com%2Fen-us%2Fbook&r=&lt=11833&evt=pageLoad&msclkid=N&sv=1&rn=70807", 
		"Resource=0", 
		"Referer=https://www.etihad.com/", 
		"Snapshot=t80.inf", 
		"Mode=HTML", 
		LAST);

	web_add_auto_header("Sec-Fetch-Dest", 
		"iframe");

	web_add_auto_header("Sec-Fetch-Mode", 
		"navigate");

	web_add_auto_header("Upgrade-Insecure-Requests", 
		"1");

	web_url("dest5.html", 
		"URL=https://etihadairways.demdex.net/dest5.html?d_nsid=0", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=https://www.etihad.com/", 
		"Snapshot=t81.inf", 
		"Mode=HTML", 
		EXTRARES, 
		"Url=https://connect.facebook.net/signals/config/189054868133229?v=2.9.33&r=stable", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://www.google.com/ads/ga-audiences?t=sr&aip=1&_r=4&slf_rd=1&v=1&_v=j88&tid=UA-36260312-1&cid=686669418.1613932802&jid=1260145807&_u=aEDAAUAAAAAAAC~&z=1330011441", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://t.co/i/adsct?type=javascript&version=1.1.0&p_id=Twitter&p_user_id=0&txn_id=nvata&events=%5B%5B%22pageview%22%2Cnull%5D%5D&tw_sale_amount=0&tw_order_quantity=0&tw_iframe_status=0&tw_document_href=https%3A%2F%2Fwww.etihad.com%2Fen-us%2Fbook", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://dpm.demdex.net/demconf.jpg?et:ibs%7cdata:dpid=411&dpuuid=YDKpMwAAAFgzbznQ", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://consent.linksynergy.com/consent/v2/p?rmch=cs&tp=gdpr&domain=www.etihad.com&sought=false&attr_sid=115172&in_scope=false&purposes=&vendors=&ext_id=c874cc18-e1a7-4d1e-bc56-d6858bb62944", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://www.google.co.il/ads/ga-audiences?t=sr&aip=1&_r=4&slf_rd=1&v=1&_v=j88&tid=UA-36260312-1&cid=686669418.1613932802&jid=1260145807&_u=aEDAAUAAAAAAAC~&z=1330011441", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://px.ads.linkedin.com/collect?v=2&fmt=js&pid=1320713&time=1613932805542&url=https%3A%2F%2Fwww.etihad.com%2Fen-us%2Fbook&cookiesTest=true", "Referer=https://www.etihad.com/", ENDITEM, 
		LAST);

	web_url("i", 
		"URL=https://tr.snapchat.com/cm/i?pid=0bbabc85-09e7-467c-9ecd-7b90cfa12395", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=https://www.etihad.com/", 
		"Snapshot=t82.inf", 
		"Mode=HTML", 
		EXTRARES, 
		"Url=../p?pid=0bbabc85-09e7-467c-9ecd-7b90cfa12395&ev=PAGE_VIEW&pl=https%3A%2F%2Fwww.etihad.com%2Fen-us%2Fbook&ts=1613932809570&rf=&v=1.5&if=false&bt=__LIVE__&e_desc=book&intg=gtm&u_c1=e4afe4b3-c85e-4188-b5a0-6c06dbfb9f1f&m_sl=18965&m_rd=19948&m_pi=11564&m_ic=0", "Referer=https://www.etihad.com/", ENDITEM, 
		LAST);

	web_add_cookie("_scid=e4afe4b3-c85e-4188-b5a0-6c06dbfb9f1f; DOMAIN=www.etihad.com");

	web_add_cookie("_fbp=fb.1.1613932809914.864711266; DOMAIN=www.etihad.com");

	web_revert_auto_header("Upgrade-Insecure-Requests");

	web_add_auto_header("Sec-Fetch-Site", 
		"same-origin");

	web_add_auto_header("Sec-Fetch-Mode", 
		"cors");

	web_add_auto_header("Sec-Fetch-Dest", 
		"empty");

	web_add_auto_header("Origin", 
		"https://www.etihad.com");

	web_custom_request("c6ed2e6ff94ti174ef6d9b189a16c414b_3", 
		"URL=https://www.etihad.com/staticweb/c6ed2e6ff94ti174ef6d9b189a16c414b", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://www.etihad.com/en-us/book", 
		"Snapshot=t83.inf", 
		"Mode=HTML", 
		"EncType=text/plain;charset=UTF-8", 
		"Body={\"sensor_data\":\"7a74G7m23Vrp0o5c9135291.67-1,2,-94,-100,Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36,uaend,12147,20030107,en-US,Gecko,3,0,0,0,397104,2801154,1440,860,1440,900,1320,725,1336,,cpen:0,i1:0,dm:0,cwen:0,non:1,opc:0,fc:0,sc:0,wrc:1,isc:0,vib:1,bat:1,x11:0,x12:1,8329,0.15719766278,806966400576,0,loc:-1,2,-94,-101,do_en,dm_en,t_en-1,2,-94,-105,0,0,0,0,1583,-1,0;-1,-1,0,0,-1,-1,0;0,0,0,0,2121,-1,0;-1,-1,0,0,-1,-1"
		",0;0,0,0,0,2751,2751,1;0,0,0,0,2783,2783,0;-1,2,-94,-102,0,0,0,0,1583,-1,0;-1,-1,0,0,-1,-1,0;0,0,0,0,2121,-1,0;-1,-1,0,0,-1,-1,0;0,0,0,0,2751,2751,1;0,0,0,0,2783,2783,0;-1,-1,1,0,-1,317,0;-1,2,-94,-108,-1,2,-94,-110,0,1,7421,531,34;1,1,7584,807,60;2,1,8151,1227,69;3,1,8423,1269,43;4,1,8785,1310,94;5,1,8794,1319,104;-1,2,-94,-117,-1,2,-94,-111,0,2697,-1,-1,-1;-1,2,-94,-109,0,2696,-1,-1,-1,-1,-1,-1,-1,-1,-1;-1,2,-94,-114,-1,2,-94,-103,-1,2,-94,-112,https://www.etihad.com/en-us/book-1,2,-94,-115,1,"
		"56078,32,2697,2696,0,61439,8796,0,1613932801152,17,17265,0,6,2877,0,0,8977,54551,0,47ACA25003F1D5F4A04DC7C4F295531C~0~YAAQL1BzaMfL87V3AQAAbeDkxQWNQETkk1l1QFnnOb1zyVZ5dK7hynlkune5xmsKwy6h/7AfMJmvxnv4VLzCwOlw6MIFOA1d3PFLBmxU4q4NYemc3xMmSvPzQ56QJQg0fB4JJq70a37UC6lKGtGQar02n86LlDX7OIQ8NHWw0X7HN1dVxS7wubgLeV9IRI15AUJhU1U1ZgKYw0HSarashnO5QeKr/RUvMlPi5T7NgkbLTGi5BpZYdrjFlk+EVmr8uzCIvWT9db35PJCCJpXH6YXHLYtPxYIPzZSpSkldIbdbRVx/t3VqUSjk4BQ19ByFgmODObjmlVGkOfhC0/y8XBCWsQpJaQ==~-1~||1-UTAPzAFFlv-1-10-1000-2||"
		"~-1,34507,251,202730061,30261693,PiZtE,34659,62-1,2,-94,-106,8,2-1,2,-94,-119,634,130,526,131,172,126,74,73,75,70,62,663,586,3426,-1,2,-94,-122,0,0,0,0,1,0,0-1,2,-94,-123,-1,2,-94,-124,0.e18a21369a377,0.9c1fa46134575,0.ce48b85687966,0.f6c0af24de8e6,0.c49d18553eadf,0.42f7115b9c049,0.250f8a5489d26,0.13ab0f7a4e15f,0.23db60c369c3c,0.5ecdb11205073;1,5,8,66,2,19,3,18,1,1;0,2,1,1,5,2,3,35,0,1;47ACA25003F1D5F4A04DC7C4F295531C,1613932801152,UTAPzAFFlv,47ACA25003F1D5F4A04DC7C4F295531C1613932801152UTAPzAFFlv"
		",1,1,0.e18a21369a377,47ACA25003F1D5F4A04DC7C4F295531C1613932801152UTAPzAFFlv10.e18a21369a377,238,200,128,196,169,182,252,41,179,146,50,62,190,48,67,182,142,153,197,101,249,231,105,192,71,102,82,3,76,190,17,247,1316,0,1613932809948;-1,2,-94,-126,-1,2,-94,-127,11321144241322243122-1,2,-94,-70,-1752250632;-915298718;dis;,7,8;true;true;true;-120;true;24;24;true;false;-1-1,2,-94,-80,5624-1,2,-94,-116,42017340-1,2,-94,-118,136301-1,2,-94,-129,"
		"ddf68f2157d3c0edf2744f0b57b5e4c63840c2c94b8cc853141f293336e1e305,1,a712c19fde04cde08d21754c81e951066896404f0eac6cbfd5255e434d879986,,,,0-1,2,-94,-121,;189;33;0\"}", 
		EXTRARES, 
		"Url=https://www.facebook.com/tr/?id=189054868133229&ev=PageView&dl=https%3A%2F%2Fwww.etihad.com%2Fen-us%2Fbook&rl=&if=false&ts=1613932809931&sw=1440&sh=900&v=2.9.33&r=stable&ec=0&o=30&fbp=fb.1.1613932809914.864711266&it=1613932808620&coo=false&tm=1&rqm=GET", "Referer=https://www.etihad.com/", ENDITEM, 
		LAST);

	web_add_cookie("_gid=GA1.2.1288094177.1613932802; DOMAIN=smetrics.etihad.com");

	web_add_cookie("scSiteEdition=en-us; DOMAIN=smetrics.etihad.com");

	web_add_cookie("_gcl_au=1.1.706136819.1613932803; DOMAIN=smetrics.etihad.com");

	web_add_cookie("_gat_UA-36260312-1=1; DOMAIN=smetrics.etihad.com");

	web_add_cookie("AMCVS_15BD401053DAEC4A0A490D4C%40AdobeOrg=1; DOMAIN=smetrics.etihad.com");

	web_add_cookie("mbox=session#acfdcd4c076546849d13a0a9b5f1f504#1613934665|PC#acfdcd4c076546849d13a0a9b5f1f504.37_0#1677177605; DOMAIN=smetrics.etihad.com");

	web_add_cookie("_ga_G21M2QMYH2=GS1.1.1613932802.1.0.1613932802.0; DOMAIN=smetrics.etihad.com");

	web_add_cookie("_ga=GA1.1.686669418.1613932802; DOMAIN=smetrics.etihad.com");

	web_add_cookie("_uetsid=38efc2d0747411eb824c037565713347; DOMAIN=smetrics.etihad.com");

	web_add_cookie("_uetvid=38f49e70747411eb84608354d038f035; DOMAIN=smetrics.etihad.com");

	web_add_cookie("_scid=e4afe4b3-c85e-4188-b5a0-6c06dbfb9f1f; DOMAIN=smetrics.etihad.com");

	web_add_cookie("_fbp=fb.1.1613932809914.864711266; DOMAIN=smetrics.etihad.com");

	web_add_cookie("AMCV_15BD401053DAEC4A0A490D4C%40AdobeOrg=1994364360%7CMCIDTS%7C18680%7CMCMID%7C26185866700827066552622528666916780474%7CMCAID%7CNONE%7CMCOPTOUT-1613940003s%7CNONE%7CMCAAMLH-1614537605%7C6%7CMCAAMB-1614537605%7Cj8Odv6LonN4r3an7LhD3WZrU1bUpAkFkkiY1ncBR96t2PTI%7CMCSYNCSOP%7C411-18687%7CvVersion%7C3.4.0; DOMAIN=smetrics.etihad.com");

	web_add_cookie("stc115172=tsa:1613932810286.445323167.50012636.5997666628335889.:20210221191010|env:1%7C20210324184010%7C20210221191010%7C1%7C1047195:20220221184010|uid:1613932810285.1674410045.7457108.115172.428744288.:20220221184010|srchist:1047195%3A1%3A20210324184010:20220221184010; DOMAIN=smetrics.etihad.com");

	web_add_cookie("s_pageName_cookie=book; DOMAIN=smetrics.etihad.com");

	web_add_cookie("s_srv=etihad-adobe-aem-hosting; DOMAIN=smetrics.etihad.com");

	web_add_cookie("s_ppvl=%5B%5BB%5D%5D; DOMAIN=smetrics.etihad.com");

	web_add_cookie("s_ppv=book%2C15%2C15%2C725%2C1320%2C725%2Cundefined%2Cundefined%2C1%2CP; DOMAIN=smetrics.etihad.com");

	web_add_cookie("s_cc=true; DOMAIN=smetrics.etihad.com");

	web_add_auto_header("Sec-Fetch-Site", 
		"cross-site");

	web_add_auto_header("Sec-Fetch-Mode", 
		"navigate");

	web_add_auto_header("Sec-Fetch-Dest", 
		"iframe");

	web_add_header("Upgrade-Insecure-Requests", 
		"1");

	web_submit_data("tr", 
		"Action=https://www.facebook.com/tr/", 
		"Method=POST", 
		"RecContentType=text/plain", 
		"Referer=https://www.etihad.com/", 
		"Snapshot=t84.inf", 
		"Mode=HTML", 
		"EncodeAtSign=YES", 
		ITEMDATA, 
		"Name=id", "Value=189054868133229", ENDITEM, 
		"Name=ev", "Value=Microdata", ENDITEM, 
		"Name=dl", "Value=https://www.etihad.com/en-us/book", ENDITEM, 
		"Name=rl", "Value=", ENDITEM, 
		"Name=if", "Value=false", ENDITEM, 
		"Name=ts", "Value=1613932810453", ENDITEM, 
		"Name=cd[DataLayer]", "Value=[]", ENDITEM, 
		"Name=cd[Meta]", "Value={\"title\":\"Book your flights with Etihad Airways USA\",\"meta:description\":\"Book your flights with Etihad Airways to Abu Dhabi and beyond. Worldwide destinations, flight offers and holidays await.\"}", ENDITEM, 
		"Name=cd[OpenGraph]", "Value={\"og:title\":\"Book\",\"og:url\":\"https://www.etihad.com/en-us/book\",\"og:site_name\":\"Etihad Global\",\"og:image\":\"https://www.etihad.com/content/dam/eag/etihadairways/etihadcom/Global/seo/Facebook_og_image.png\",\"og:type\":\"website\",\"og:description\":\"Book your flights with Etihad Airways to Abu Dhabi and beyond. Worldwide destinations, flight offers and holidays await.\",\"twitter:title\":\"Book\",\"twitter:description\":\"Book your flights with Etihad "
		"Airways to Abu Dhabi and beyond. Worldwide destinations, flight offers and holidays await.\",\"twitter:image\":\"https://www.etihad.com/content/dam/eag/etihadairways/etihadcom/Global/seo/Facebook_og_image.png\",\"twitter:card\":\"summary_large_image\"}", ENDITEM, 
		"Name=cd[Schema.org]", "Value=[]", ENDITEM, 
		"Name=cd[JSON-LD]", "Value=[{\"@context\":\"https://schema.org\",\"@type\":\"Airline\",\"name\":\"Etihad Airways\",\"url\":\"https://www.etihad.com\",\"logo\":\"/content/dam/eag/etihadairways/etihadcom/Global/logo/header/etihad-airways-logo-191219.svg\",\"sameAs\":[\"https://www.facebook.com/etihad.airways\",\"https://www.linkedin.com/company/etihadairways\",\"https://www.instagram.com/etihadairways/\",\"https://twitter.com/etihadairways\"]},{\"@context\":\"https://schema.org\",\"@type\":\""
		"BreadcrumbList\",\"itemListElement\":[{\"item\":\"https://www.etihad.com/en-us/book\",\"@type\":\"ListItem\",\"name\":\"Book\",\"position\":1}]}]", ENDITEM, 
		"Name=sw", "Value=1440", ENDITEM, 
		"Name=sh", "Value=900", ENDITEM, 
		"Name=v", "Value=2.9.33", ENDITEM, 
		"Name=r", "Value=stable", ENDITEM, 
		"Name=ec", "Value=1", ENDITEM, 
		"Name=o", "Value=30", ENDITEM, 
		"Name=fbp", "Value=fb.1.1613932809914.864711266", ENDITEM, 
		"Name=it", "Value=1613932808620", ENDITEM, 
		"Name=coo", "Value=false", ENDITEM, 
		"Name=es", "Value=automatic", ENDITEM, 
		"Name=tm", "Value=3", ENDITEM, 
		"Name=rqm", "Value=formPOST", ENDITEM, 
		EXTRARES, 
		"Url=https://assets.adobedtm.com/8aea536f4a27/88ed4f88d8f6/b952b1e8a447/RC0d3cea422d0441dab592032f659f99e4-source.min.js", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://smetrics.etihad.com/b/ss/etihaddotcomprod/10/JS-2.17.0-LBQ1/s82190947405658?AQB=1&ndh=1&pf=1&callback=s_c_il[1].doPostbacks&et=1&t=21%2F1%2F2021%2020%3A40%3A11%200%20-120&d.&nsid=0&jsonv=1&.d&sdid=6A3F59C891286C9C-67A9CA130B15A327&mid=26185866700827066552622528666916780474&aamlh=6&ce=UTF-8&ns=etihadairways&cdp=2&pageName=book&g=https%3A%2F%2Fwww.etihad.com%2Fen-us%2Fbook&cc=AED&ch=eyey&server=etihad-adobe-aem-hosting&aamb=j8Odv6LonN4r3an7LhD3WZrU1bUpAkFkkiY1ncBR96t2PTI&c1=D%3Dv1&v1="
		"en-us&l2=home-_-flightsearch-_-return-_-20190821%2Chome-_-flightsearch-_-oneway-_-20190821%2C%2C&v13=AED&c15=D%3Dv15&v15=en-us%3Abook&c16=8%3A40%20PM%7CSunday&v16=8%3A40%20PM%7CSunday&c17=D%3Dv17&c22=D%3Dv22&v22=en-us&v27=book&c29=D%3Dv29&v29=D%3Dg&v54=1613932798754&c56=D%3Dv56&v56=desktop&v57=en&v58=D%3DpageName&v60=prod&c73=D%3Dv73&v73=7.0.0&v87=annonymous&v89=D%3Dmid&v127=%3A&v149=www.etihad.com&v150=launch&v199=False&v249=true&s=1440x900&c=24&j=1.6&v=N&k=Y&bw=1320&bh=725&mcorgid="
		"15BD401053DAEC4A0A490D4C%40AdobeOrg&AQE=1", "Referer=https://www.etihad.com/", ENDITEM, 
		LAST);

	lr_end_transaction("Vu_LaunchApp_transaction",LR_AUTO);

	web_revert_auto_header("Origin");

	web_revert_auto_header("sec-ch-ua");

	web_revert_auto_header("sec-ch-ua-mobile");

	web_add_auto_header("Sec-Fetch-Site", 
		"none");

	web_add_auto_header("Sec-Fetch-Mode", 
		"no-cors");

	web_add_auto_header("Sec-Fetch-Dest", 
		"empty");

	web_custom_request("json_3", 
		"URL=https://update.googleapis.com/service/update2/json", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=", 
		"Snapshot=t85.inf", 
		"Mode=HTML", 
		"EncType=application/json", 
		"Body={\"request\":{\"@os\":\"win\",\"@updater\":\"chrome\",\"acceptformat\":\"crx2,crx3\",\"app\":[{\"appid\":\"kiabhabjdbkjdpjbpigfodbdjmbglcoo\",\"event\":[{\"download_time_ms\":18217,\"downloaded\":9322,\"downloader\":\"bits\",\"eventresult\":1,\"eventtype\":14,\"nextversion\":\"2021.2.21.2\",\"previousversion\":\"2021.2.20.6\",\"total\":9322,\"url\":\"http://redirector.gvt1.com/edgedl/release2/chrome_component/dYwgtcWOe4xDRS28F3o0ag_2021.2.21.2/AJmFR19mkM3x049VMcZID8Y\"},{\"eventresult\":1,\""
		"eventtype\":3,\"nextfp\":\"1.0cc442c315869272b4a0aa7b19a0529e99c19a98f36f5947dd2f4ccd7f1c7b2a\",\"nextversion\":\"2021.2.21.2\",\"previousfp\":\"1.d5d11284fd7f1f100e83d84a6517db52c6711f4dec784f9bd5b89220ea8d20a8\",\"previousversion\":\"2021.2.20.6\"}],\"version\":\"2021.2.21.2\"}],\"arch\":\"x64\",\"dedup\":\"cr\",\"hw\":{\"physmemory\":4},\"lang\":\"en-US\",\"nacl_arch\":\"x86-64\",\"os\":{\"arch\":\"x86_64\",\"platform\":\"Windows\",\"version\":\"10.0.19042.804\"},\"prodversion\":\""
		"88.0.4324.182\",\"protocol\":\"3.1\",\"requestid\":\"{6b0e3f08-ada4-4bee-9123-bf43bfa5a8fc}\",\"sessionid\":\"{fcf76159-fa26-44ec-8419-d6e6c99e4fd1}\",\"updaterversion\":\"88.0.4324.182\"}}", 
		EXTRARES, 
		"Url=https://analytics.twitter.com/i/adsct?type=javascript&version=1.1.0&p_id=Twitter&p_user_id=0&txn_id=nvata&events=%5B%5B%22pageview%22%2Cnull%5D%5D&tw_sale_amount=0&tw_order_quantity=0&tw_iframe_status=0&tpx_cb=twttr.conversion.loadPixels&tw_document_href=https%3A%2F%2Fwww.etihad.com%2Fen-us%2Fbook", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://cdn.mouseflow.com/projects/226ff167-994e-4acb-85f6-1678320b3aad.js", "Referer=https://www.etihad.com/", ENDITEM, 
		"Url=https://tracking.bd4travel.com/module/y20107/bd4t.js", "Referer=https://www.etihad.com/", ENDITEM, 
		LAST);

	web_add_auto_header("Sec-Fetch-Mode", 
		"cors");

	web_add_header("Origin", 
		"https://www.etihad.com");

	web_add_auto_header("Sec-Fetch-Site", 
		"cross-site");

	web_add_auto_header("sec-ch-ua", 
		"\"Chromium\";v=\"88\", \"Google Chrome\";v=\"88\", \";Not A Brand\";v=\"99\"");

	web_add_auto_header("sec-ch-ua-mobile", 
		"?0");

	web_custom_request("collect_3", 
		"URL=https://tracking.bd4travel.com/collect?y20107", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=text/plain", 
		"Referer=https://www.etihad.com/", 
		"Snapshot=t86.inf", 
		"Mode=HTML", 
		"EncType=application/x-www-form-urlencoded; charset=UTF-8", 
		"Body=ed="
		"%7B%22context%22%3A%7B%22pageInfo%22%3A%7B%22pageName%22%3A%22book%22%2C%22env%22%3A%22prod%22%2C%22server%22%3A%22etihad-adobe-AEM-hosting%22%2C%22siteEdition%22%3A%22en-us%22%2C%22sysEnv%22%3A%22Desktop%22%2C%22version%22%3A%227.0.0%22%2C%22language%22%3A%22en%22%2C%22currencyCode%22%3A%22AED%22%2C%22pageInternalCampaignCode%22%3A%7B%220%22%3A%22home-_-flightsearch-_-return-_-20190821%22%2C%221%22%3A%22home-_-flightsearch-_-oneway-_-20190821%22%7D%7D%2C%22category%22%3A%7B%22siteSection%22%3A%22"
		"eyey%22%2C%22primaryCategory%22%3A%22book%22%7D%2C%22routeMap%22%3A%7B%7D%2C%22error%22%3A%7B%7D%2C%22loginStatus%22%3A%22annonymous%22%2C%22userDetail%22%3A%7B%7D%7D%2C%22version%22%3A6%2C%22subversion%22%3A2%2C%22timestamp%22%3A%222021-02-21T18%3A40%3A24.192Z%22%2C%22trackingId%22%3A%22y20107%22%2C%22userId%22%3A%22Sl68_InJJTao.1613932824190%22%2C%22action%22%3A%22pageview%22%2C%22documentLocation%22%3A%22https%3A%2F%2Fwww.etihad.com%2Fen-us%2Fbook%22%2C%22documentReferrer%22%3A%22%22%2C%22userA"
		"gent%22%3A%22Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20Win64%3B%20x64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F88.0.4324.182%20Safari%2F537.36%22%2C%22versions%22%3A%7B%22bd4tracker%22%3A%227.6.0%22%7D%2C%22privacySetting%22%3Anull%2C%22trackingRegion%22%3A%22innovo%22%7D", 
		LAST);

	web_add_auto_header("Sec-Fetch-Mode", 
		"navigate");

	web_add_auto_header("Sec-Fetch-Dest", 
		"iframe");

	web_add_header("Upgrade-Insecure-Requests", 
		"1");

	web_url("latest", 
		"URL=https://tracking.bd4travel.com/cdn/guid-app/latest/", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=https://www.etihad.com/", 
		"Snapshot=t87.inf", 
		"Mode=HTML", 
		LAST);

	lr_start_transaction("Vu_FlightSelection_transaction");

	web_add_auto_header("Sec-Fetch-Site", 
		"same-origin");

	web_add_auto_header("Sec-Fetch-Mode", 
		"cors");

	web_add_auto_header("Sec-Fetch-Dest", 
		"empty");

	web_url("AUH.json", 
		"URL=https://www.etihad.com/edge-services/ns/all-ond-auto/v210/en/AUH.json", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://www.etihad.com/en-us/book", 
		"Snapshot=t88.inf", 
		"Mode=HTML", 
		LAST);

	lr_end_transaction("Vu_FlightSelection_transaction",LR_AUTO);

	lr_start_transaction("Vu_SearchFlight_transaction");

	web_add_auto_header("Sec-Fetch-Site", 
		"cross-site");

	web_add_auto_header("Sec-Fetch-Dest", 
		"iframe");

	web_add_header("Origin", 
		"https://www.etihad.com");

	web_add_auto_header("Sec-Fetch-Mode", 
		"navigate");

	web_add_auto_header("Sec-Fetch-User", 
		"?1");

	web_add_auto_header("Upgrade-Insecure-Requests", 
		"1");

	lr_think_time(20);

	web_submit_data("tr_2", 
		"Action=https://www.facebook.com/tr/", 
		"Method=POST", 
		"RecContentType=text/plain", 
		"Referer=https://www.etihad.com/", 
		"Snapshot=t89.inf", 
		"Mode=HTML", 
		ITEMDATA, 
		"Name=id", "Value=189054868133229", ENDITEM, 
		"Name=ev", "Value=SubscribedButtonClick", ENDITEM, 
		"Name=dl", "Value=https://www.etihad.com/en-us/book", ENDITEM, 
		"Name=rl", "Value=", ENDITEM, 
		"Name=if", "Value=false", ENDITEM, 
		"Name=ts", "Value=1613932874734", ENDITEM, 
		"Name=cd[buttonFeatures]", "Value={\"classList\":\"button col_left \",\"destination\":\"https://www.etihad.com/en-us/book\",\"id\":\"\",\"imageUrl\":\"\",\"innerText\":\"Search flights\",\"numChildButtons\":0,\"tag\":\"button\",\"name\":\"\",\"value\":\"\"}", ENDITEM, 
		"Name=cd[buttonText]", "Value=Search flights", ENDITEM, 
		"Name=cd[formFeatures]", "Value=[{\"id\":\"roundTrip\",\"name\":\"radioGroup\",\"tag\":\"input\",\"inputType\":\"radio\"},{\"id\":\"oneWay\",\"name\":\"radioGroup\",\"tag\":\"input\",\"inputType\":\"radio\"},{\"id\":\"multiWay\",\"name\":\"radioGroup\",\"tag\":\"input\",\"inputType\":\"radio\"},{\"id\":\"oneWayOrigin\",\"name\":\"\",\"tag\":\"input\",\"inputType\":\"text\"},{\"id\":\"\",\"name\":\"\",\"tag\":\"input\",\"inputType\":null,\"valueMeaning\":\"empty\"},{\"id\":\"\",\"name\":\"\",\"tag\""
		":\"button\"},{\"id\":\"\",\"name\":\"\",\"tag\":\"button\"},{\"id\":\"oneWayDestination\",\"name\":\"\",\"tag\":\"input\",\"inputType\":\"text\"},{\"id\":\"\",\"name\":\"\",\"tag\":\"input\",\"inputType\":null,\"valueMeaning\":\"empty\"},{\"id\":\"\",\"name\":\"\",\"tag\":\"button\"},{\"id\":\"oneWayCalendarDepartDate\",\"name\":\"oneWayCalendarDepartDate\",\"tag\":\"input\",\"placeholder\":\"DD MMM YYYY\",\"inputType\":\"text\"},{\"id\":\"dd-guest-class-type\",\"name\":\"\",\"tag\":\"button\"},{"
		"\"id\":\"dd-passenger-type\",\"name\":\"\",\"tag\":\"button\"},{\"id\":\"passenger-type-dec-0\",\"name\":\"\",\"tag\":\"button\"},{\"id\":\"passenger-type-inc-0\",\"name\":\"\",\"tag\":\"button\"},{\"id\":\"passenger-type-dec-1\",\"name\":\"\",\"tag\":\"button\"},{\"id\":\"passenger-type-inc-1\",\"name\":\"\",\"tag\":\"button\"},{\"id\":\"passenger-type-dec-2\",\"name\":\"\",\"tag\":\"button\"},{\"id\":\"passenger-type-inc-2\",\"name\":\"\",\"tag\":\"button\"},{\"id\":\"redemptionsearchslider\",\""
		"name\":\"\",\"tag\":\"input\",\"inputType\":\"checkbox\"}]", ENDITEM, 
		"Name=cd[pageFeatures]", "Value={\"title\":\"Book your flights with Etihad Airways USA\"}", ENDITEM, 
		"Name=cd[parameters]", "Value=[]", ENDITEM, 
		"Name=sw", "Value=1440", ENDITEM, 
		"Name=sh", "Value=900", ENDITEM, 
		"Name=v", "Value=2.9.33", ENDITEM, 
		"Name=r", "Value=stable", ENDITEM, 
		"Name=ec", "Value=2", ENDITEM, 
		"Name=o", "Value=30", ENDITEM, 
		"Name=fbp", "Value=fb.1.1613932809914.864711266", ENDITEM, 
		"Name=it", "Value=1613932808620", ENDITEM, 
		"Name=coo", "Value=false", ENDITEM, 
		"Name=es", "Value=automatic", ENDITEM, 
		"Name=tm", "Value=3", ENDITEM, 
		"Name=rqm", "Value=formPOST", ENDITEM, 
		LAST);

	web_add_cookie("check=true; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("Etihad.com_AEM_SiteEdition=en-us; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_gid=GA1.2.1288094177.1613932802; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("scSiteEdition=en-us; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_gcl_au=1.1.706136819.1613932803; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AMCVS_15BD401053DAEC4A0A490D4C%40AdobeOrg=1; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("mbox=session#acfdcd4c076546849d13a0a9b5f1f504#1613934665|PC#acfdcd4c076546849d13a0a9b5f1f504.37_0#1677177605; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_ga_G21M2QMYH2=GS1.1.1613932802.1.0.1613932802.0; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_ga=GA1.1.686669418.1613932802; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_uetsid=38efc2d0747411eb824c037565713347; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_uetvid=38f49e70747411eb84608354d038f035; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_scid=e4afe4b3-c85e-4188-b5a0-6c06dbfb9f1f; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_fbp=fb.1.1613932809914.864711266; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AMCV_15BD401053DAEC4A0A490D4C%40AdobeOrg=1994364360%7CMCIDTS%7C18680%7CMCMID%7C26185866700827066552622528666916780474%7CMCAID%7CNONE%7CMCOPTOUT-1613940003s%7CNONE%7CMCAAMLH-1614537605%7C6%7CMCAAMB-1614537605%7Cj8Odv6LonN4r3an7LhD3WZrU1bUpAkFkkiY1ncBR96t2PTI%7CMCSYNCSOP%7C411-18687%7CvVersion%7C3.4.0; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("stc115172=tsa:1613932810286.445323167.50012636.5997666628335889.:20210221191010|env:1%7C20210324184010%7C20210221191010%7C1%7C1047195:20220221184010|uid:1613932810285.1674410045.7457108.115172.428744288.:20220221184010|srchist:1047195%3A1%3A20210324184010:20220221184010; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("s_pageName_cookie=book; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("s_srv=etihad-adobe-aem-hosting; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("s_ppvl=%5B%5BB%5D%5D; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("s_cc=true; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("s_visit=1; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_sctr=1|1613858400000; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AAMC_etihadairways_0=REGION%7C6; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("mf_226ff167-994e-4acb-85f6-1678320b3aad=|.46435224.1613932822942|1613932822960||0|||0|17.37|58.53512; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("bd4ti=Sl68_InJJTao.1613932824190; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("s_ptc=%5B%5BB%5D%5D; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("s_ppv=book%2C26%2C15%2C1258%2C1320%2C725%2Cundefined%2Cundefined%2C1%2CP; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("s_sq=etihaddotcomprod%3D%2526c.%2526a.%2526activitymap.%2526page%253Dbook%2526link%253DSearch%252520flights%2526region%253Dflightsearch%2526pageIDType%253D1%2526.activitymap%2526.a%2526.c%2526pid%253Dbook%2526pidt%253D1%2526oid%253Dfunctionlt%252528%252529%25257B%25257D%2526oidt%253D2%2526ot%253DBUTTON; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("SSW=ReturnUrl=https://www.etihad.com/en-us/book&IsResidenceSelected=false&VIPRoute=false; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("SearchPanelConfig=SearchPanelConfig={\"SearchedSource\":\"Abu Dhabi (AUH)\",\"SearchedDest\":\"Bengaluru (BLR)\",\"SrcCntryCode\":\"AE\",\"DestCntryCode\":\"IN\",\"LandingPage\":\"https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-20en\",\"BookingType\":1,\"HotelsEnabled\":\"False\",\"CarsEnabled\":\"False\",\"SiteEdition\":\"en-us\",\"hdnTT\":"
		"\"\",\"SelectedPaxDetails\":{\"A\":\"1\",\"C\":\"0\",\"I\":\"0\",\"Y\":\"0\"},\"TrvlCls\":{\"ECO\":{\"MaxPax\":9,\"MinPax\":1},\"BSINSS\":{\"MaxPax\":9,\"MinPax\":1},\"FIRST\":{\"MaxPax\":9,\"MinPax\":1},\"VFIRST\":{\"MaxPax\":2,\"MinPax\":1,\"VIPSaleStrtDt\":\"27/06/2014\",\"VIPEnLoc\":\"SYD,LHR,JFK,CDG,ICN\"}},\"Pax\":{\"limitIndivPax\":\"False\",\"MultOfTwo\":\"False\",\"A\":{\"MaxPax\":9,\"MinPax\":1,\"MaxAdlts\":9,\"MinAdlts\":1,\"GetAOpt\":9},\"I\":{\"GetIOpt\":2},\"C\":{\"MinC\":0,\"MaxC\""
		":0,\"GetCOpt\":9}}}; DOMAIN=dxbooking.etihad.com");

	web_revert_auto_header("Sec-Fetch-Dest");

	web_add_header("Sec-Fetch-Dest", 
		"document");

	web_revert_auto_header("Sec-Fetch-Site");

	web_add_header("Sec-Fetch-Site", 
		"same-site");

	web_add_cookie("_ga_G21M2QMYH2=GS1.1.1613932802.1.1.1613932877.0; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("___utmvc=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; DOMAIN=dxbooking.etihad.com");

	web_url("EYDX", 
		"URL=https://dxbooking.etihad.com/dx/EYDX/", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=https://www.etihad.com/", 
		"Snapshot=t90.inf", 
		"Mode=HTML", 
		EXTRARES, 
		"Url=/_Incapsula_Resource?SWKMTFSR=1&e=0.20467588179735063", ENDITEM, 
		LAST);

	web_revert_auto_header("Sec-Fetch-Mode");

	web_revert_auto_header("Sec-Fetch-User");

	web_revert_auto_header("Upgrade-Insecure-Requests");

	web_revert_auto_header("sec-ch-ua");

	web_revert_auto_header("sec-ch-ua-mobile");

	web_custom_request("upload", 
		"URL=https://beacons.gcp.gvt2.com/domainreliability/upload", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=application/javascript", 
		"Referer=", 
		"Snapshot=t91.inf", 
		"Mode=HTML", 
		"EncType=application/json; charset=utf-8", 
		"Body={\"entries\":[{\"http_response_code\":200,\"network_changed\":false,\"protocol\":\"QUIC\",\"request_age_ms\":71845,\"request_elapsed_ms\":183,\"sample_rate\":0.05,\"server_ip\":\"216.58.204.70:443\",\"status\":\"ok\",\"url\":\"https://4338867.fls.doubleclick.net/\",\"was_proxied\":false}],\"reporter\":\"chrome\"}", 
		LAST);

	web_custom_request("upload_2", 
		"URL=https://google.co.il/domainreliability/upload", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=application/javascript", 
		"Referer=", 
		"Snapshot=t92.inf", 
		"Mode=HTML", 
		"EncType=application/json; charset=utf-8", 
		"Body={\"entries\":[{\"failure_data\":{\"custom_error\":\"net::ERR_CERT_COMMON_NAME_INVALID\"},\"network_changed\":false,\"protocol\":\"\",\"request_age_ms\":67473,\"request_elapsed_ms\":681,\"sample_rate\":1.0,\"server_ip\":\"\",\"status\":\"ssl.cert.name_invalid\",\"url\":\"https://adservice.google.co.il/\",\"was_proxied\":false},{\"failure_data\":{\"custom_error\":\"net::ERR_CERT_COMMON_NAME_INVALID\"},\"network_changed\":false,\"protocol\":\"\",\"request_age_ms\":50768,\"request_elapsed_ms\""
		":940,\"sample_rate\":1.0,\"server_ip\":\"\",\"status\":\"ssl.cert.name_invalid\",\"url\":\"https://adservice.google.co.il/\",\"was_proxied\":false}],\"reporter\":\"chrome\"}", 
		LAST);

	web_add_auto_header("Sec-Fetch-Site", 
		"cross-site");

	web_add_auto_header("Sec-Fetch-Mode", 
		"no-cors");

	web_add_auto_header("Sec-Fetch-Dest", 
		"empty");

	web_add_header("Origin", 
		"https://www.etihad.com");

	web_add_header("sec-ch-ua", 
		"\"Chromium\";v=\"88\", \"Google Chrome\";v=\"88\", \";Not A Brand\";v=\"99\"");

	web_add_header("sec-ch-ua-mobile", 
		"?0");

	web_custom_request("0_2", 
		"URL=https://bat.bing.com/actionp/0?ti=4017239&Ver=2&mid=9efc6fd9-1b65-4ebc-9e62-ff4af90f9b14&sid=38efc2d0747411eb824c037565713347&vid=38f49e70747411eb84608354d038f035&vids=1&evt=pageHide", 
		"Method=POST", 
		"Resource=0", 
		"Referer=https://www.etihad.com/", 
		"Snapshot=t93.inf", 
		"Mode=HTML", 
		"EncType=text/plain;charset=UTF-8", 
		LAST);

	web_revert_auto_header("Sec-Fetch-Dest");

	web_revert_auto_header("Sec-Fetch-Mode");

	web_revert_auto_header("Sec-Fetch-Site");

	web_add_header("Origin", 
		"https://google.co.il");

	web_add_header("Access-Control-Request-Headers", 
		"content-type");

	web_add_header("Access-Control-Request-Method", 
		"POST");

	web_custom_request("upload-nel", 
		"URL=https://beacons2.gvt2.com/domainreliability/upload-nel", 
		"Method=OPTIONS", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=", 
		"Snapshot=t94.inf", 
		"Mode=HTML", 
		LAST);

	web_add_header("Sec-Fetch-Mode", 
		"cors");

	web_add_header("Sec-Fetch-Dest", 
		"empty");

	web_add_header("Origin", 
		"https://dxbooking.etihad.com");

	web_add_auto_header("Sec-Fetch-Site", 
		"same-origin");

	web_add_auto_header("sec-ch-ua", 
		"\"Chromium\";v=\"88\", \"Google Chrome\";v=\"88\", \";Not A Brand\";v=\"99\"");

	web_add_auto_header("sec-ch-ua-mobile", 
		"?0");

	web_custom_request("vnkinder-as-it-doe-as-to-a-clips-with-so-frauenl", 
		"URL=https://dxbooking.etihad.com/vnkinder-as-it-doe-as-to-a-clips-with-so-frauenl?d=dxbooking.etihad.com", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/_Incapsula_Resource?CWUDNSAI=41&xinfo=13-23383024-0%20NNNN%20RT%281613932920843%20574%29%20q%280%20-1%20-1%200%29%20r%281%20-1%29%20B10%2814%2c0%2c0%29%20U5&incident_id=1168000290027278954-138522509372819277&edet=10&cinfo=0e000000ee26&rpinfo=0", 
		"Snapshot=t95.inf", 
		"Mode=HTML", 
		"EncType=text/plain; charset=utf-8", 
		"Body={\"solution\":{\"interrogation\":{\"p\":\"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\",\"st\":1613932721,\"sr\":2203333701,\"cr\":210928339},\"version\":\"stable\"},\"old_token\":null,\"error\":null,\"performance\":{\"interrogation\":613}}", 
		LAST);

	web_add_cookie("reese84=3:iI3nZ1r+2u3bISVyzsbgxA==:AmK58rBu/FCQBrBxaVytxWGz5gsH6dyWwo4xK1FsFmnczi6wAUuexgczYoMXqFOOQ61Te+JWFAJLCUB64OUPAq2X5H7XFF8L9RNw2fv+H0w0uYlqncHEa+x92MOeWPym3CDm5xgQwY9PJWtzEtsRic0VKaABlodtmwuqW7KN/zqnGee9mAUQgKSOg3Q4m1obXL4YLWRbY8n7UwPdegprt2+BQ0xYp5Dz0ig7DGnpxgWmgLCPMksfRYqUovC1oQTr8Nu00BGePE4hpvq+JugeIEfQcPm1R79ZftAKxoEUvwbCS3Z6dLaJwcv8wu5k/Uv0C/Y61GPq0hhOJ1H/3Cz53IEZkgRXTBMsRfhCuw0Jn6YsrBfIw3p6A1zel0irzcE8OjqlVX8TdlYavVovKAb5EsB6uieXUQ14d5GixJ8WyOA="
		":GYld9KWDH5g6hX7UdiBhhpNfPEhX8xFqBaGkljQqZ+w=; DOMAIN=dxbooking.etihad.com");

	web_add_auto_header("Sec-Fetch-Mode", 
		"navigate");

	web_add_auto_header("Sec-Fetch-Dest", 
		"document");

	web_add_header("Upgrade-Insecure-Requests", 
		"1");

	web_add_cookie("AWSALB=5I8nLlCYkt65e23yQ6AoTdFQQ/EYIs3iw1EGXIHkCkzwG6mdql9uf2+xCTc4ToYrGyzq+RC95p9+Q7sbCvftAXzkzz7mVb1NxjMGHe5ouYtOtbi7gFD/Uo0J7AWl; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALBCORS=5I8nLlCYkt65e23yQ6AoTdFQQ/EYIs3iw1EGXIHkCkzwG6mdql9uf2+xCTc4ToYrGyzq+RC95p9+Q7sbCvftAXzkzz7mVb1NxjMGHe5ouYtOtbi7gFD/Uo0J7AWl; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALB=vRdmoMFUe1+UuRzhAAEwRTzfwUAP3ijpozpT0NkrYb00JrXa7mIwAiJzsgcMGPhjW7d+cvtfKu7QySydtUDQCXrH4Ft89QbVXKwYDZnCi6TTGvHF9NoSg5CwQHXa; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALBCORS=vRdmoMFUe1+UuRzhAAEwRTzfwUAP3ijpozpT0NkrYb00JrXa7mIwAiJzsgcMGPhjW7d+cvtfKu7QySydtUDQCXrH4Ft89QbVXKwYDZnCi6TTGvHF9NoSg5CwQHXa; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALB=LnOwkQM9GbrXfFHhjtZx4glHl2C7yjuz7wXWWF4eJhk5up7R+FxPprx9spss+gu9ZO0x3OoQBxHn2GaUGKgMd/1vK2oaZB21i6qAdcfzN0TwrGCu4T90Fud09wlj; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALBCORS=LnOwkQM9GbrXfFHhjtZx4glHl2C7yjuz7wXWWF4eJhk5up7R+FxPprx9spss+gu9ZO0x3OoQBxHn2GaUGKgMd/1vK2oaZB21i6qAdcfzN0TwrGCu4T90Fud09wlj; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALB=vTq85eDxs6gLPfgCs6gi4mY6wlOa4AVoteEQ1O31OnaTyLIlE9ffBIcTcebksMZ1AMjKPKMlSLxk5RiaEZJ2t5Zufyl6KyfDxa2ABMIMqwMHAER5HDl7ONFJgGkv; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALBCORS=vTq85eDxs6gLPfgCs6gi4mY6wlOa4AVoteEQ1O31OnaTyLIlE9ffBIcTcebksMZ1AMjKPKMlSLxk5RiaEZJ2t5Zufyl6KyfDxa2ABMIMqwMHAER5HDl7ONFJgGkv; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALB=NhRXRwW6vGjZF1PMFMbWS58yfb3iZMOhzABitIK3F4cR8QonVJZYYsoVJP4oCP53FNc+GghKQUCr9bEwrgSnqsTHqhyJH2GTMGyA8U2Gwzc0guwmTPzmep8HrkyY; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALBCORS=NhRXRwW6vGjZF1PMFMbWS58yfb3iZMOhzABitIK3F4cR8QonVJZYYsoVJP4oCP53FNc+GghKQUCr9bEwrgSnqsTHqhyJH2GTMGyA8U2Gwzc0guwmTPzmep8HrkyY; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("bm_sz=325B51BE6EBB36655AF63A49E555E7A4~YAAQL1BzaL7L87V3AQAAuMrkxQrBo8SLQvRDmDy8XF0hOtBs87Uff/VgXpyC3bv0YWm5bzXEaOBy9RlToErhEjXonUt9goIDmAuj+muEPfp8jY+JdnWQBGE9Q5kexYUQgleioXJlG8HWUbV8wkxTU+t8vDV4wGqkl2njtLHGwbVlY0rN4NHIxp6sNRBX+H/y; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("ak_bmsc=A9F9127680F343DD461B5FC9E5463D586873502F241C000029A9326087F17029~plaxa/Cp4zd3qSzpJLmP3uUIDkoliTRLX9T/DrDUhlnItwYqt9u3k9Hh1BF8UvHnXMdOVJq82GzdbKO3ZdVDKsmiXdCCuMmlnMDQNzHxEo1F+BHIun7EndxTiap2y5ZcxFKlbYztGdqGWOxbwEMuuPICokVTkBPlaTfEpOlltpzjL0aoiqxgno26poQFwfkHrbf3I8nIjXm+GpdJvJ0xVfiq+S46H9prXHgR+fDZ1GEkWA2jqa2FnznZhP/xvhaCzU; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_abck=47ACA25003F1D5F4A04DC7C4F295531C~0~YAAQL1BzaN3L87V3AQAA0/3kxQW9oNCNcjvSyl2JXnugs5UOE6EOOE5wliBlX7RRNm+VO/S74BMWAqam3wPqwIhVwBiccacb2RnT42cUGU4WYsFhafZN7ApLyw/KXDQWUm7boIxZ2Zq0ZRjC/S4AC1DYzSW3vSeitmW64KL6YfmBXwuytDYG7FabecL+hUgPCglbXVS72+IZ6tZu1FKNkMRs3uEsAGHpc20oBuKd6YDY0YAkOTqr/zoszDR6Xyl1A7+5p5goE7Z3r4CJTdLyV+7RTfiHmxgNOKjT4c2/DXMzM1rRFszr00EAZV4quNqVmMtExf09U7oxLfsuKkzvDrSvPUOsgg==~-1~||-1||~-1; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("visid_incap_2042951=+a45m09xTc2QjCqgL2pSPoSpMmAAAAAAQUIPAAAAAABB8074cy8jBqwFwU9Xjxak; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("incap_ses_1168_2042951=StLsZ2OaFzmBFqFQE5I1EISpMmAAAAAAhPiIugo5f9yNcH97igeUYg==; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_ga=GA1.3.686669418.1613932802; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_gid=GA1.3.1288094177.1613932802; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_ga_G21M2QMYH2=GS1.1.1613932802.1.1.1613932890.0; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("mbox=session#acfdcd4c076546849d13a0a9b5f1f504#1613934751|PC#acfdcd4c076546849d13a0a9b5f1f504.37_0#1677177605; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_ga=GA1.2.686669418.1613932802; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("_gat_UA-36260312-1=1; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("amp_4cf3bd=ELJFL_0mHM4Cy3LlBTlmwf.Y2tsZmh6cTNoMDB0NWhpc2d3b3I2cjcyNg==..1ev2ub488.1ev2ub48u.3.2.5; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("4687.vst=%7B%22s%22%3A%22e9039aa0-4c8a-4e4e-8cad-c70670988702%22%2C%22t%22%3A%22new%22%2C%22lu%22%3A1613932896827%2C%22lv%22%3A1613932896827%2C%22lp%22%3A0%7D; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("amp_4cf3bd=ELJFL_0mHM4Cy3LlBTlmwf.Y2tsZmh6cTNoMDB0NWhpc2d3b3I2cjcyNg==..1ev2ub488.1ev2ub9u4.4.2.6; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALB=RXhPaLULvTdn4+MHLGwzIZ9NKW5CYrGhBWk6P8z/j3t8zi8Uf+46Gc5THlHCXtsZkaQgxLNAItsNQqlwcHMZKM3JBxiMykXdS3JgjT+X2DpR1OcvXm8cy7N6TBH1; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALBCORS=RXhPaLULvTdn4+MHLGwzIZ9NKW5CYrGhBWk6P8z/j3t8zi8Uf+46Gc5THlHCXtsZkaQgxLNAItsNQqlwcHMZKM3JBxiMykXdS3JgjT+X2DpR1OcvXm8cy7N6TBH1; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("emcid=F-cOkLiag2; DOMAIN=dxbooking.etihad.com");

	web_url("EYDX_2", 
		"URL=https://dxbooking.etihad.com/dx/EYDX/", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=https://dxbooking.etihad.com/dx/EYDX/", 
		"Snapshot=t96.inf", 
		"Mode=HTML", 
		EXTRARES, 
		"Url=https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/fonts/loader.svg", "Referer=https://dxbooking.etihad.com/dx/EYDX/4.5.60-151.MMCOPY4.5.60-150/css/main.css", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/js/26.js", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/images/beacon.gif?actions=SET_SELECTED_TRIP_OPTION,APP_LOAD_INIT,GLOBAL_CONFIGURATION_REQUEST&a_GLOBAL_CONFIGURATION_t=64&ipcc=EYDX&buildNo=0&version=&releaseName=4.5.60-151.MMCOPY4.5.60-150&page=undefined&url=undefined&flow=BOOKING", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/js/moment/locale/en-gb.js", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/images/logo.png", "Referer=https://dxbooking.etihad.com/dx/EYDX/4.5.60-151.MMCOPY4.5.60-150/css/custom.css", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/images/beacon.gif?actions=AIRLINE_INTERLINE_ROUTES,SIGN_IN_CONFIGURATION,SIGN_UP_CONFIGURATION,HEADER_CONFIGURATION,FOOTER_CONFIGURATION,PROVINCES_CONFIGURATION,VALIDATIONS,RULES_CONFIGURATION,PHONE_CONFIGURATION,AIRLINE_ROUTES,TRANSLATION_REQUEST,LOAD_STATIC_TEXTS_REQUEST&w_APP_LOAD_t=4486&ipcc=EYDX&buildNo=0&version=&releaseName=4.5.60-151.MMCOPY4.5.60-150&page=flight-selection&url="
		"https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021&flow=BOOKING", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/images/beacon.gif?actions=SET_SESSION_START_OVER_PARAMS,SESSION_REQUESTED&a_TRANSLATION_t=1285&ipcc=EYDX&buildNo=0&version=&releaseName=4.5.60-151.MMCOPY4.5.60-150&page=flight-selection&url=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021&flow=BOOKING", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/fonts/spark-icon-line.woff", "Referer=https://dxbooking.etihad.com/dx/EYDX/4.5.60-151.MMCOPY4.5.60-150/css/main.css", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/images/beacon.gif?actions=&a_LOAD_STATIC_TEXTS_t=560&ipcc=EYDX&buildNo=0&version=&releaseName=4.5.60-151.MMCOPY4.5.60-150&page=flight-selection&url=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021&flow=BOOKING", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/data/customjs/custom.js", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/js/styles.js", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/js/b2c/ancillaries-view.js", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/css/styles.css", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/images/beacon.gif?requestUrl=https%3A%2F%2Fdc.etihad.com%2Fv4.3%2Fssw%2Flogin%3Fjipcc%3DEYDX&requestMethod=GET&duration=0&responseStatus=NotProcessed&xhrStatus=401&xhrStatusText=Unauthorized&errorCode=ERR.SSW.LOGIN.USER_NOT_AUTHENTICATED&errorMessage=Cannot%20retrieve%20login%20result%20information&ipcc=EYDX&buildNo=0&version=&releaseName=4.5.60-151.MMCOPY4.5.60-150&page=flight-selection&url="
		"https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021&flow=BOOKING", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/images/beacon.gif?actions=BRAND_BONUS_CONFIGURATION,,FLIGHT_SELECTION_METADATA,UPSELL_METADATA,SEAT_SELECTION_METADATA&ipcc=EYDX&buildNo=0&version=&releaseName=4.5.60-151.MMCOPY4.5.60-150&page=flight-selection&url=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021&flow=BOOKING", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/fonts/spark-icon-fill.woff", "Referer=https://dxbooking.etihad.com/dx/EYDX/4.5.60-151.MMCOPY4.5.60-150/css/main.css", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/images/beacon.gif?actions=THRESHOLD_NOT_MET_FOR_SOME_FLIGHTS_CLEAR,CART_SET_DEFAULT,FLIGHT_SELECTION_CLEAR_ERROR,SEARCH_FLIGHTS_REQUEST,ADVISORY_MESSAGES_REQUEST,GEOLOCATION_REQUEST&a_GEOLOCATION_t=799&ipcc=EYDX&buildNo=0&version=&releaseName=4.5.60-151.MMCOPY4.5.60-150&page=flight-selection&url="
		"https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021&flow=BOOKING", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/js/b2c/cars-view.js", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/images/airlines/AI.png", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/images/airlines/UL.png", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/images/airlines/EY.png", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/images/beacon.gif?pageRendered=flight-selection&ipcc=EYDX&buildNo=0&version=&releaseName=4.5.60-151.MMCOPY4.5.60-150&page=flight-selection&url=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021&flow=BOOKING&&duration=22242.42499999958&count=1", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/images/beacon.gif?actions=PASSENGERS_METADATA,COUNTRIES_MAPPING,SEARCH_CALL_COMPLETED&a_ADVISORY_MESSAGES_t=11147&ipcc=EYDX&buildNo=0&version=&releaseName=4.5.60-151.MMCOPY4.5.60-150&page=flight-selection&url=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021&flow=BOOKING", ENDITEM, 
		"Url=4.5.60-151.MMCOPY4.5.60-150/images/beacon.gif?actions=&a_SEARCH_FLIGHTS_t=11150&ipcc=EYDX&buildNo=0&version=&releaseName=4.5.60-151.MMCOPY4.5.60-150&page=flight-selection&url=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021&flow=BOOKING", ENDITEM, 
		LAST);

	web_add_cookie("AWSALB=Noi8I4TJrD+eXjFVdHZgItFcYAanJzDVq3m4FDME2t1btTV8jT8aVullUd+Smf64QOQaifq4x/E3xaqOm/sv5vCUO0VTEeE9vr0TFgY/ckkv7PDFxloO9+7W2fJi; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALBCORS=Noi8I4TJrD+eXjFVdHZgItFcYAanJzDVq3m4FDME2t1btTV8jT8aVullUd+Smf64QOQaifq4x/E3xaqOm/sv5vCUO0VTEeE9vr0TFgY/ckkv7PDFxloO9+7W2fJi; DOMAIN=dxbooking.etihad.com");

	web_add_header("Origin", 
		"https://dxbooking.etihad.com");

	web_add_auto_header("Sec-Fetch-Dest", 
		"empty");

	web_add_auto_header("Sec-Fetch-Mode", 
		"cors");

	web_custom_request("vnkinder-as-it-doe-as-to-a-clips-with-so-frauenl_2", 
		"URL=https://dxbooking.etihad.com/vnkinder-as-it-doe-as-to-a-clips-with-so-frauenl?d=dxbooking.etihad.com", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/dx/EYDX/", 
		"Snapshot=t97.inf", 
		"Mode=HTML", 
		"EncType=text/plain; charset=utf-8", 
		"Body=\"3:iI3nZ1r+2u3bISVyzsbgxA==:AmK58rBu/FCQBrBxaVytxWGz5gsH6dyWwo4xK1FsFmnczi6wAUuexgczYoMXqFOOQ61Te+JWFAJLCUB64OUPAq2X5H7XFF8L9RNw2fv+H0w0uYlqncHEa+x92MOeWPym3CDm5xgQwY9PJWtzEtsRic0VKaABlodtmwuqW7KN/zqnGee9mAUQgKSOg3Q4m1obXL4YLWRbY8n7UwPdegprt2+BQ0xYp5Dz0ig7DGnpxgWmgLCPMksfRYqUovC1oQTr8Nu00BGePE4hpvq+JugeIEfQcPm1R79ZftAKxoEUvwbCS3Z6dLaJwcv8wu5k/Uv0C/Y61GPq0hhOJ1H/3Cz53IEZkgRXTBMsRfhCuw0Jn6YsrBfIw3p6A1zel0irzcE8OjqlVX8TdlYavVovKAb5EsB6uieXUQ14d5GixJ8WyOA="
		":GYld9KWDH5g6hX7UdiBhhpNfPEhX8xFqBaGkljQqZ+w=\"", 
		LAST);

	web_add_cookie("nlbi_2042947_2147483646=LDupHyL8Kjy3CsQN68CAIwAAAABJ61cwbOCDVwhaoTj6we28; DOMAIN=dxbooking.etihad.com");

	web_url("metadata.json", 
		"URL=https://dxbooking.etihad.com/dx/EYDX/4.5.60-151.MMCOPY4.5.60-150/data/global/metadata.json", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/dx/EYDX/", 
		"Snapshot=t98.inf", 
		"Mode=HTML", 
		LAST);

	web_add_cookie("AWSALB=6Sz8OiJue6NCPJWhH5pHhIVoaXTLgWwNBYNMbp+IQUOGfRDyAcNO7myyHlb0RKLkndQPylQJ51ejtqWos7g5wArQ97/rwE6yjC7Pxtikuz2rv1fnVu2awVXdONbL; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALBCORS=6Sz8OiJue6NCPJWhH5pHhIVoaXTLgWwNBYNMbp+IQUOGfRDyAcNO7myyHlb0RKLkndQPylQJ51ejtqWos7g5wArQ97/rwE6yjC7Pxtikuz2rv1fnVu2awVXdONbL; DOMAIN=dxbooking.etihad.com");

	web_url("routes.json", 
		"URL=https://dxbooking.etihad.com/dx/EYDX/4.5.60-151.MMCOPY4.5.60-150/data/global/routes.json", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/dx/EYDX/", 
		"Snapshot=t99.inf", 
		"Mode=HTML", 
		LAST);

	web_url("en-GB.json", 
		"URL=https://dxbooking.etihad.com/dx/EYDX/4.5.60-151.MMCOPY4.5.60-150/data/translations/en-GB.json", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/dx/EYDX/", 
		"Snapshot=t100.inf", 
		"Mode=HTML", 
		LAST);

	web_add_cookie("AWSALB=hiVlES8xiKql4yImbZkjH+KmqtTmPT0WUMQQPmSQ2krlUf89Y6dUScJeTwF0C2zXWoaiiBrAjYhIZM2K1fkrgYJKuQHZx6jzPRR62SYue7OUHLcGUoDu10kVI/BI; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALBCORS=hiVlES8xiKql4yImbZkjH+KmqtTmPT0WUMQQPmSQ2krlUf89Y6dUScJeTwF0C2zXWoaiiBrAjYhIZM2K1fkrgYJKuQHZx6jzPRR62SYue7OUHLcGUoDu10kVI/BI; DOMAIN=dxbooking.etihad.com");

	web_url("staticTexts.json", 
		"URL=https://dxbooking.etihad.com/dx/EYDX/4.5.60-151.MMCOPY4.5.60-150/data/static-texts/staticTexts.json", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/dx/EYDX/", 
		"Snapshot=t101.inf", 
		"Mode=HTML", 
		LAST);

	web_url("brandBonusesConfiguration.json", 
		"URL=https://dxbooking.etihad.com/dx/EYDX/4.5.60-151.MMCOPY4.5.60-150/data/features/brandBonusesConfiguration.json", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/dx/EYDX/", 
		"Snapshot=t102.inf", 
		"Mode=HTML", 
		LAST);

	web_url("flightSelectionConfiguration.json", 
		"URL=https://dxbooking.etihad.com/dx/EYDX/4.5.60-151.MMCOPY4.5.60-150/data/features/flightSelectionConfiguration.json", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/dx/EYDX/", 
		"Snapshot=t103.inf", 
		"Mode=HTML", 
		LAST);

	web_url("upsellConfiguration.json", 
		"URL=https://dxbooking.etihad.com/dx/EYDX/4.5.60-151.MMCOPY4.5.60-150/data/features/upsellConfiguration.json", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/dx/EYDX/", 
		"Snapshot=t104.inf", 
		"Mode=HTML", 
		LAST);

	web_revert_auto_header("sec-ch-ua");

	web_revert_auto_header("sec-ch-ua-mobile");

	web_add_auto_header("Sec-Fetch-Site", 
		"same-site");

	web_add_header("Access-Control-Request-Headers", 
		"authorization,conversation-id");

	web_add_header("Access-Control-Request-Method", 
		"GET");

	web_add_header("Origin", 
		"https://dxbooking.etihad.com");

	web_custom_request("login", 
		"URL=https://dc.etihad.com/v4.3/ssw/login?jipcc=EYDX", 
		"Method=OPTIONS", 
		"Resource=0", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t105.inf", 
		"Mode=HTML", 
		LAST);

	web_add_auto_header("Sec-Fetch-Site", 
		"same-origin");

	web_add_auto_header("sec-ch-ua", 
		"\"Chromium\";v=\"88\", \"Google Chrome\";v=\"88\", \";Not A Brand\";v=\"99\"");

	web_add_auto_header("sec-ch-ua-mobile", 
		"?0");

	web_url("seatSelectionConfiguration.json", 
		"URL=https://dxbooking.etihad.com/dx/EYDX/4.5.60-151.MMCOPY4.5.60-150/data/features/seatSelectionConfiguration.json", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/dx/EYDX/", 
		"Snapshot=t106.inf", 
		"Mode=HTML", 
		EXTRARES, 
		"Url=https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		LAST);

	web_add_auto_header("Sec-Fetch-Mode", 
		"no-cors");

	web_add_auto_header("Sec-Fetch-Dest", 
		"image");

	web_add_auto_header("Sec-Fetch-Site", 
		"cross-site");

	web_url("0_3", 
		"URL=https://bat.bing.com/action/0?ti=4017239&Ver=2&mid=64f67633-7d49-4bf4-9870-70b4b3f091c7&sid=38efc2d0747411eb824c037565713347&vid=38f49e70747411eb84608354d038f035&vids=0&pi=1200101525&lg=en-US&sw=1440&sh=900&sc=24&tl=Etihad%20Airways%20-%20Flight%20selection&p=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021&r="
		"https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F&lt=4486&evt=pageLoad&msclkid=N&sv=1&rn=603373", 
		"Resource=0", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t107.inf", 
		"Mode=HTML", 
		LAST);

	web_add_auto_header("Sec-Fetch-Mode", 
		"cors");

	web_add_auto_header("Sec-Fetch-Dest", 
		"empty");

	web_add_header("Origin", 
		"https://dxbooking.etihad.com");

	web_url("json_4", 
		"URL=https://etihadairways.tt.omtrdc.net/m2/etihadairways/mbox/json?mbox=target-global-mbox&mboxSession=acfdcd4c076546849d13a0a9b5f1f504&mboxPC=acfdcd4c076546849d13a0a9b5f1f504.37_0&mboxPage=88f4aefb4165430eb9529989f5fa1e90&mboxRid=fe9de8134cb94449a63fabf262ec04c5&mboxVersion=1.7.0&mboxCount=1&mboxTime=1613940089675&mboxHost=dxbooking.etihad.com&mboxURL="
		"https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021&mboxReferrer=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F&mboxXDomain=enabled&browserHeight=725&browserWidth=1303&browserTimeOffset=120&screenHeight=900&screenWidth=1440&colorDepth=24&devicePixelRatio=1&screenOrientation=landscape&webGLRenderer=Google%20SwiftShader&flowType=booking&language=en&"
		"products=flights%3B%3B1%3B0&tntaction=load&dxPageName=flight-selection&at_property=864a2d44-7fb8-1f02-f8d0-d680f344979c&journeyType=one-way&siteEdition=en-us&FlightADTPax=1&FlightCHDPax=0&FlightINFPax=0&dx-searchOND=economy%3Aauh%3Ablr&oneappsource=&redemptionflow=&bookingCurrency=&Analyticspagename=eydx%3Aflight-selection&Inbound_days_left=&dx-searchdnrDates=30%2F04%2F2021&inBoundCabinClass=&Outbound_days_left=67&outBoundCabinClass=&mboxMCSDID=07527C11692ADC04-1DC54A1454C83E02&vst.trk="
		"metrics.etihad.com&vst.trks=smetrics.etihad.com&mboxMCGVID=26185866700827066552622528666916780474&mboxAAMB=j8Odv6LonN4r3an7LhD3WZrU1bUpAkFkkiY1ncBR96t2PTI&mboxMCGLH=6", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t108.inf", 
		"Mode=HTML", 
		EXTRARES, 
		"Url=https://consent.linksynergy.com/consent/v2/p?rmch=cs&tp=gdpr&domain=dxbooking.etihad.com&sought=false&attr_sid=115172&in_scope=false&purposes=&vendors=&ext_id=ae17a2d8-3354-4249-886a-2bcb7be1c336", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		"Url=https://cdn.amplitude.com/libs/amplitude-7.1.0-min.gz.js", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		"Url=https://assets.adobedtm.com/extensions/EPb3826f174b534354aaa5a9e9f1dab55d/AppMeasurement_Module_AudienceManagement.min.js", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		LAST);

	web_add_auto_header("Sec-Fetch-Mode", 
		"navigate");

	web_add_auto_header("Sec-Fetch-Dest", 
		"iframe");

	web_add_header("Upgrade-Insecure-Requests", 
		"1");

	web_url("latest_2", 
		"URL=https://tracking.bd4travel.com/cdn/guid-app/latest/", 
		"Resource=0", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t109.inf", 
		"Mode=HTML", 
		EXTRARES, 
		"Url=https://static.ads-twitter.com/uwt.js", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		LAST);

	web_add_auto_header("Origin", 
		"https://dxbooking.etihad.com");

	web_add_auto_header("Sec-Fetch-Dest", 
		"empty");

	web_add_auto_header("Sec-Fetch-Mode", 
		"cors");

	web_add_auto_header("Sec-Fetch-Site", 
		"same-site");

	web_url("countrycode", 
		"URL=https://www.etihad.com/edge-services/countrycode", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t110.inf", 
		"Mode=HTML", 
		LAST);

	web_revert_auto_header("sec-ch-ua");

	web_revert_auto_header("sec-ch-ua-mobile");

	web_add_header("Access-Control-Request-Headers", 
		"authorization,conversation-id");

	web_add_header("Access-Control-Request-Method", 
		"GET");

	web_custom_request("init", 
		"URL=https://dc.etihad.com/v4.3/ssw/products/init?jipcc=EYDX", 
		"Method=OPTIONS", 
		"Resource=0", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t111.inf", 
		"Mode=HTML", 
		LAST);

	web_add_auto_header("Sec-Fetch-Site", 
		"cross-site");

	web_add_auto_header("sec-ch-ua", 
		"\"Chromium\";v=\"88\", \"Google Chrome\";v=\"88\", \";Not A Brand\";v=\"99\"");

	web_add_auto_header("sec-ch-ua-mobile", 
		"?0");

	web_custom_request("collect_4", 
		"URL=https://tracking.bd4travel.com/collect?y20107", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=text/plain", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t112.inf", 
		"Mode=HTML", 
		"EncType=application/x-www-form-urlencoded; charset=UTF-8", 
		"Body=ed="
		"%7B%22context%22%3A%7B%22pageInfo%22%3A%7B%22pageName%22%3A%22flight-selection%22%2C%22language%22%3A%22en-GB%22%2C%22flowType%22%3A%22BOOKING%22%7D%2C%22productInfo%22%3A%7B%22journeyType%22%3A%22one-way%22%2C%22passengerCount%22%3A1%2C%22ADT%22%3A1%2C%22class%22%3A%22Economy%22%2C%22origin%22%3A%22AUH%22%2C%22destination%22%3A%22BLR%22%2C%22date%22%3A%2204-30-2021%22%2C%22awardBooking%22%3Afalse%2C%22origins%22%3A%7B%220%22%3A%22AUH%22%7D%2C%22destinations%22%3A%7B%220%22%3A%22BLR%22%7D%2C%22dat"
		"es%22%3A%7B%220%22%3A%2204-30-2021%22%7D%7D%7D%2C%22version%22%3A6%2C%22subversion%22%3A2%2C%22timestamp%22%3A%222021-02-21T18%3A41%3A31.563Z%22%2C%22trackingId%22%3A%22y20107%22%2C%22userId%22%3A%22Sl68_InJJTao.1613932824190%22%2C%22action%22%3A%22pageview%22%2C%22documentLocation%22%3A%22https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021%22%2C%22docu"
		"mentReferrer%22%3A%22https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%22%2C%22userAgent%22%3A%22Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20Win64%3B%20x64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F88.0.4324.182%20Safari%2F537.36%22%2C%22versions%22%3A%7B%22bd4tracker%22%3A%227.6.0%22%7D%2C%22privacySetting%22%3Anull%2C%22trackingRegion%22%3A%22innovo%22%7D", 
		EXTRARES, 
		"Url=https://config1.veinteractive.com/tags/D9D2B911/A3A0/4FA4/8F93/08084164B50A/tag.js", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		"Url=https://www.securitytrfx.com/js/ey_new_ibe.js", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		LAST);

	web_add_cookie("bm_sz=325B51BE6EBB36655AF63A49E555E7A4~YAAQL1BzaL7L87V3AQAAuMrkxQrBo8SLQvRDmDy8XF0hOtBs87Uff/VgXpyC3bv0YWm5bzXEaOBy9RlToErhEjXonUt9goIDmAuj+muEPfp8jY+JdnWQBGE9Q5kexYUQgleioXJlG8HWUbV8wkxTU+t8vDV4wGqkl2njtLHGwbVlY0rN4NHIxp6sNRBX+H/y; DOMAIN=dc.etihad.com");

	web_add_cookie("check=true; DOMAIN=dc.etihad.com");

	web_add_cookie("Etihad.com_AEM_SiteEdition=en-us; DOMAIN=dc.etihad.com");

	web_add_cookie("_gid=GA1.2.1288094177.1613932802; DOMAIN=dc.etihad.com");

	web_add_cookie("ak_bmsc=A9F9127680F343DD461B5FC9E5463D586873502F241C000029A9326087F17029~plaxa/Cp4zd3qSzpJLmP3uUIDkoliTRLX9T/DrDUhlnItwYqt9u3k9Hh1BF8UvHnXMdOVJq82GzdbKO3ZdVDKsmiXdCCuMmlnMDQNzHxEo1F+BHIun7EndxTiap2y5ZcxFKlbYztGdqGWOxbwEMuuPICokVTkBPlaTfEpOlltpzjL0aoiqxgno26poQFwfkHrbf3I8nIjXm+GpdJvJ0xVfiq+S46H9prXHgR+fDZ1GEkWA2jqa2FnznZhP/xvhaCzU; DOMAIN=dc.etihad.com");

	web_add_cookie("scSiteEdition=en-us; DOMAIN=dc.etihad.com");

	web_add_cookie("_gcl_au=1.1.706136819.1613932803; DOMAIN=dc.etihad.com");

	web_add_cookie("AMCVS_15BD401053DAEC4A0A490D4C%40AdobeOrg=1; DOMAIN=dc.etihad.com");

	web_add_cookie("_scid=e4afe4b3-c85e-4188-b5a0-6c06dbfb9f1f; DOMAIN=dc.etihad.com");

	web_add_cookie("_fbp=fb.1.1613932809914.864711266; DOMAIN=dc.etihad.com");

	web_add_cookie("AMCV_15BD401053DAEC4A0A490D4C%40AdobeOrg=1994364360%7CMCIDTS%7C18680%7CMCMID%7C26185866700827066552622528666916780474%7CMCAID%7CNONE%7CMCOPTOUT-1613940003s%7CNONE%7CMCAAMLH-1614537605%7C6%7CMCAAMB-1614537605%7Cj8Odv6LonN4r3an7LhD3WZrU1bUpAkFkkiY1ncBR96t2PTI%7CMCSYNCSOP%7C411-18687%7CvVersion%7C3.4.0; DOMAIN=dc.etihad.com");

	web_add_cookie("stc115172=tsa:1613932810286.445323167.50012636.5997666628335889.:20210221191010|env:1%7C20210324184010%7C20210221191010%7C1%7C1047195:20220221184010|uid:1613932810285.1674410045.7457108.115172.428744288.:20220221184010|srchist:1047195%3A1%3A20210324184010:20220221184010; DOMAIN=dc.etihad.com");

	web_add_cookie("_abck=47ACA25003F1D5F4A04DC7C4F295531C~0~YAAQL1BzaN3L87V3AQAA0/3kxQW9oNCNcjvSyl2JXnugs5UOE6EOOE5wliBlX7RRNm+VO/S74BMWAqam3wPqwIhVwBiccacb2RnT42cUGU4WYsFhafZN7ApLyw/KXDQWUm7boIxZ2Zq0ZRjC/S4AC1DYzSW3vSeitmW64KL6YfmBXwuytDYG7FabecL+hUgPCglbXVS72+IZ6tZu1FKNkMRs3uEsAGHpc20oBuKd6YDY0YAkOTqr/zoszDR6Xyl1A7+5p5goE7Z3r4CJTdLyV+7RTfiHmxgNOKjT4c2/DXMzM1rRFszr00EAZV4quNqVmMtExf09U7oxLfsuKkzvDrSvPUOsgg==~-1~||-1||~-1; DOMAIN=dc.etihad.com");

	web_add_cookie("s_pageName_cookie=book; DOMAIN=dc.etihad.com");

	web_add_cookie("s_srv=etihad-adobe-aem-hosting; DOMAIN=dc.etihad.com");

	web_add_cookie("s_ppvl=%5B%5BB%5D%5D; DOMAIN=dc.etihad.com");

	web_add_cookie("s_cc=true; DOMAIN=dc.etihad.com");

	web_add_cookie("s_visit=1; DOMAIN=dc.etihad.com");

	web_add_cookie("_sctr=1|1613858400000; DOMAIN=dc.etihad.com");

	web_add_cookie("AAMC_etihadairways_0=REGION%7C6; DOMAIN=dc.etihad.com");

	web_add_cookie("mf_226ff167-994e-4acb-85f6-1678320b3aad=|.46435224.1613932822942|1613932822960||0|||0|17.37|58.53512; DOMAIN=dc.etihad.com");

	web_add_cookie("bd4ti=Sl68_InJJTao.1613932824190; DOMAIN=dc.etihad.com");

	web_add_cookie("s_ptc=%5B%5BB%5D%5D; DOMAIN=dc.etihad.com");

	web_add_cookie("s_ppv=book%2C26%2C15%2C1258%2C1320%2C725%2Cundefined%2Cundefined%2C1%2CP; DOMAIN=dc.etihad.com");

	web_add_cookie("s_sq=etihaddotcomprod%3D%2526c.%2526a.%2526activitymap.%2526page%253Dbook%2526link%253DSearch%252520flights%2526region%253Dflightsearch%2526pageIDType%253D1%2526.activitymap%2526.a%2526.c%2526pid%253Dbook%2526pidt%253D1%2526oid%253Dfunctionlt%252528%252529%25257B%25257D%2526oidt%253D2%2526ot%253DBUTTON; DOMAIN=dc.etihad.com");

	web_add_cookie("SSW=ReturnUrl=https://www.etihad.com/en-us/book&IsResidenceSelected=false&VIPRoute=false; DOMAIN=dc.etihad.com");

	web_add_cookie("SearchPanelConfig=SearchPanelConfig={\"SearchedSource\":\"Abu Dhabi (AUH)\",\"SearchedDest\":\"Bengaluru (BLR)\",\"SrcCntryCode\":\"AE\",\"DestCntryCode\":\"IN\",\"LandingPage\":\"https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-20en\",\"BookingType\":1,\"HotelsEnabled\":\"False\",\"CarsEnabled\":\"False\",\"SiteEdition\":\"en-us\",\"hdnTT\":"
		"\"\",\"SelectedPaxDetails\":{\"A\":\"1\",\"C\":\"0\",\"I\":\"0\",\"Y\":\"0\"},\"TrvlCls\":{\"ECO\":{\"MaxPax\":9,\"MinPax\":1},\"BSINSS\":{\"MaxPax\":9,\"MinPax\":1},\"FIRST\":{\"MaxPax\":9,\"MinPax\":1},\"VFIRST\":{\"MaxPax\":2,\"MinPax\":1,\"VIPSaleStrtDt\":\"27/06/2014\",\"VIPEnLoc\":\"SYD,LHR,JFK,CDG,ICN\"}},\"Pax\":{\"limitIndivPax\":\"False\",\"MultOfTwo\":\"False\",\"A\":{\"MaxPax\":9,\"MinPax\":1,\"MaxAdlts\":9,\"MinAdlts\":1,\"GetAOpt\":9},\"I\":{\"GetIOpt\":2},\"C\":{\"MinC\":0,\"MaxC\""
		":0,\"GetCOpt\":9}}}; DOMAIN=dc.etihad.com");

	web_add_cookie("reese84=3:iI3nZ1r+2u3bISVyzsbgxA==:AmK58rBu/FCQBrBxaVytxWGz5gsH6dyWwo4xK1FsFmnczi6wAUuexgczYoMXqFOOQ61Te+JWFAJLCUB64OUPAq2X5H7XFF8L9RNw2fv+H0w0uYlqncHEa+x92MOeWPym3CDm5xgQwY9PJWtzEtsRic0VKaABlodtmwuqW7KN/zqnGee9mAUQgKSOg3Q4m1obXL4YLWRbY8n7UwPdegprt2+BQ0xYp5Dz0ig7DGnpxgWmgLCPMksfRYqUovC1oQTr8Nu00BGePE4hpvq+JugeIEfQcPm1R79ZftAKxoEUvwbCS3Z6dLaJwcv8wu5k/Uv0C/Y61GPq0hhOJ1H/3Cz53IEZkgRXTBMsRfhCuw0Jn6YsrBfIw3p6A1zel0irzcE8OjqlVX8TdlYavVovKAb5EsB6uieXUQ14d5GixJ8WyOA="
		":GYld9KWDH5g6hX7UdiBhhpNfPEhX8xFqBaGkljQqZ+w=; DOMAIN=dc.etihad.com");

	web_add_cookie("JSESSIONID=E16D682ABD7D150359795F1AC48B6695; DOMAIN=dc.etihad.com");

	web_add_cookie("visid_incap_2042951=+a45m09xTc2QjCqgL2pSPoSpMmAAAAAAQUIPAAAAAABB8074cy8jBqwFwU9Xjxak; DOMAIN=dc.etihad.com");

	web_add_cookie("incap_ses_1168_2042951=StLsZ2OaFzmBFqFQE5I1EISpMmAAAAAAhPiIugo5f9yNcH97igeUYg==; DOMAIN=dc.etihad.com");

	web_add_cookie("_uetsid=38efc2d0747411eb824c037565713347; DOMAIN=dc.etihad.com");

	web_add_cookie("_uetvid=38f49e70747411eb84608354d038f035; DOMAIN=dc.etihad.com");

	web_add_cookie("_ga_G21M2QMYH2=GS1.1.1613932802.1.1.1613932890.0; DOMAIN=dc.etihad.com");

	web_add_cookie("mbox=session#acfdcd4c076546849d13a0a9b5f1f504#1613934751|PC#acfdcd4c076546849d13a0a9b5f1f504.37_0#1677177605; DOMAIN=dc.etihad.com");

	web_add_cookie("_ga=GA1.2.686669418.1613932802; DOMAIN=dc.etihad.com");

	web_add_cookie("_gat_UA-36260312-1=1; DOMAIN=dc.etihad.com");

	web_add_auto_header("Sec-Fetch-Site", 
		"same-site");

	web_revert_auto_header("Origin");

	web_add_header("Conversation-ID", 
		"cklfhzq3h00t5hisgwor6r726");
//
//	web_url("init_2", 
//		"URL=https://dc.etihad.com/v4.3/ssw/products/init?jipcc=EYDX", 
//		"Resource=0", 
//		"Referer=https://dxbooking.etihad.com/", 
//		"Snapshot=t113.inf", 
//		"Mode=HTML", 
//		EXTRARES, 
//		"Url=https://safebrowsing.googleapis.com/v4/fullHashes_find?$req="
//		"Ch0KDGdvb2dsZWNocm9tZRINODguMC40MzI0LjE4MhIbCg0IBRAGGAEiAzAwMTABEO7GChoCGAYi9XTxEhsKDQgBEAYYASIDMDAxMAEQ2MQIGgIYBkvzzxsSGwoNCAMQBhgBIgMwMDEwARCjwAgaAhgGwM8UERIbCg0IBxAGGAEiAzAwMTABEPz_CBoCGAbWb5j-EhkKDQgBEAYYASIDMDAxMAMQFBoCGAb7ks9FEhoKDQgBEAgYASIDMDAxMAQQyx0aAhgG4huuHBIZCg0ICRAGGAEiAzAwMTAGEAMaAhgG1oMHbhIaCg0IDxAGGAEiAzAwMTABEIBaGgIYBrmABXYSGQoNCAoQCBgBIgMwMDEwARAGGgIYBlJAG0gSGQoNCAkQBhgBIgMwMDEwARAfGgIYBkLlFDsSGgoNCAgQBhgBIgMwMDEwARC1CxoCGAbbCZ4KEhsKDQgNEAYYASIDMDAxMAEQrIQBGgIYBjr2ASYSGwoNCA4QBh"
//		"gBIgMwMDEwARCLiwUaAhgGve44VBIaCg0IEBAGGAEiAzAwMTABEPMIGgIYBkuNNlEaLAgBCAMIBQgGCAcICAgJCAoIDQgOCA8IEBABEAgaBgoET2uwryABIAMgBCAG&$ct=application/x-protobuf&key=AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw", "Referer=", ENDITEM, 
//		LAST);

	web_add_auto_header("Sec-Fetch-Site", 
		"cross-site");

	web_add_auto_header("Origin", 
		"https://dxbooking.etihad.com");

	web_url("json_5", 
		"URL=https://etihadairways.tt.omtrdc.net/m2/etihadairways/mbox/json?mbox=target-global-mbox&mboxSession=acfdcd4c076546849d13a0a9b5f1f504&mboxPC=acfdcd4c076546849d13a0a9b5f1f504.37_0&mboxPage=88f4aefb4165430eb9529989f5fa1e90&mboxRid=4704d5d4b63a4347932ce68a35c87b30&mboxVersion=1.7.0&mboxCount=2&mboxTime=1613940093034&mboxHost=dxbooking.etihad.com&mboxURL="
		"https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021%26execution%3De1s1&mboxReferrer=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F&mboxXDomain=enabled&browserHeight=725&browserWidth=1320&browserTimeOffset=120&screenHeight=900&screenWidth=1440&colorDepth=24&devicePixelRatio=1&screenOrientation=landscape&webGLRenderer=Google%20SwiftShader&flowType="
		"booking&language=en&products=flights%3B%3B1%3B0&tntaction=hashchange&dxPageName=flight-selection&at_property=864a2d44-7fb8-1f02-f8d0-d680f344979c&journeyType=one-way&siteEdition=en-us&FlightADTPax=1&FlightCHDPax=0&FlightINFPax=0&dx-searchOND=economy%3Aauh%3Ablr&oneappsource=&redemptionflow=&bookingCurrency=&Analyticspagename=eydx%3Aflight-selection&Inbound_days_left=&dx-searchdnrDates=30%2F04%2F2021&inBoundCabinClass=&Outbound_days_left=67&outBoundCabinClass=&dx-pageName=flight-selection&"
		"mboxMCSDID=1284866E93776425-6639B370B7E27198&vst.trk=metrics.etihad.com&vst.trks=smetrics.etihad.com&mboxMCGVID=26185866700827066552622528666916780474&mboxAAMB=j8Odv6LonN4r3an7LhD3WZrU1bUpAkFkkiY1ncBR96t2PTI&mboxMCGLH=6", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t114.inf", 
		"Mode=HTML", 
		LAST);

	web_revert_auto_header("sec-ch-ua");

	web_revert_auto_header("sec-ch-ua-mobile");

	web_add_auto_header("Sec-Fetch-Site", 
		"same-site");

	web_add_header("Access-Control-Request-Headers", 
		"authorization,content-type,conversation-id");

	web_add_header("Access-Control-Request-Method", 
		"POST");

	web_custom_request("search", 
		"URL=https://dc.etihad.com/v4.3/ssw/products/air/search?execution=e1s1&jipcc=EYDX", 
		"Method=OPTIONS", 
		"Resource=0", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t115.inf", 
		"Mode=HTML", 
		EXTRARES, 
		"Url=https://www.securitytrfx.com/js/ey/ey_v3.0.js", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		"Url=https://config1.veinteractive.com/scripts/5.0/capture-apps-5.0.0.js", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		"Url=https://analytics.twitter.com/i/adsct?type=javascript&version=1.1.0&p_id=Twitter&p_user_id=0&txn_id=nvata&events=%5B%5B%22pageview%22%2Cnull%5D%5D&tw_sale_amount=0&tw_order_quantity=0&tw_iframe_status=0&tpx_cb=twttr.conversion.loadPixels&tw_document_href=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021%26execution%3De1s1", "Referer=https://"
		"dxbooking.etihad.com/", ENDITEM, 
		"Url=https://t.co/i/adsct?type=javascript&version=1.1.0&p_id=Twitter&p_user_id=0&txn_id=nvata&events=%5B%5B%22pageview%22%2Cnull%5D%5D&tw_sale_amount=0&tw_order_quantity=0&tw_iframe_status=0&tw_document_href=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021%26execution%3De1s1", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		LAST);

	web_revert_auto_header("Origin");

	web_add_auto_header("Sec-Fetch-Mode", 
		"navigate");

	web_add_auto_header("Sec-Fetch-Dest", 
		"iframe");

	web_add_auto_header("Sec-Fetch-Site", 
		"cross-site");

	web_add_header("Upgrade-Insecure-Requests", 
		"1");

	web_add_auto_header("sec-ch-ua", 
		"\"Chromium\";v=\"88\", \"Google Chrome\";v=\"88\", \";Not A Brand\";v=\"99\"");

	web_add_auto_header("sec-ch-ua-mobile", 
		"?0");

	web_url("dest5.html_2", 
		"URL=https://etihadairways.demdex.net/dest5.html?d_nsid=0", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t116.inf", 
		"Mode=HTML", 
		LAST);

	web_add_auto_header("Sec-Fetch-Mode", 
		"cors");

	web_add_auto_header("Sec-Fetch-Dest", 
		"empty");

	web_add_header("Origin", 
		"https://dxbooking.etihad.com");

	web_custom_request("api.amplitude.com", 
		"URL=https://api.amplitude.com/", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t117.inf", 
		"Mode=HTML", 
		"EncType=application/x-www-form-urlencoded; charset=UTF-8", 
		"Body=checksum=54a9f0d038e3d878e0eab31462f9a883&client=4cf3bd807cf1e9ef73f4b82bce06478a&e="
		"%5B%7B%22device_id%22%3A%22ELJFL_0mHM4Cy3LlBTlmwf%22%2C%22user_id%22%3Anull%2C%22timestamp%22%3A1613932892428%2C%22event_id%22%3A1%2C%22session_id%22%3A1613932892424%2C%22event_type%22%3A%22%24identify%22%2C%22version_name%22%3Anull%2C%22platform%22%3A%22Web%22%2C%22os_name%22%3A%22Chrome%22%2C%22os_version%22%3A%2288%22%2C%22device_model%22%3A%22Windows%22%2C%22device_manufacturer%22%3Anull%2C%22language%22%3A%22en-US%22%2C%22carrier%22%3Anull%2C%22api_properties%22%3A%7B%7D%2C%22event_properties"
		"%22%3A%7B%7D%2C%22user_properties%22%3A%7B%22%24setOnce%22%3A%7B%22initial_referrer%22%3A%22https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%22%2C%22initial_referring_domain%22%3A%22dxbooking.etihad.com%22%7D%2C%22%24set%22%3A%7B%22referrer%22%3A%22https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%22%2C%22referring_domain%22%3A%22dxbooking.etihad.com%22%7D%7D%2C%22uuid%22%3A%22d90c9620-ae32-4464-8e98-1b82b0a82a1c%22%2C%22library%22%3A%7B%22name%22%3A%22amplitude-js%22%2C%22version%22%3A%227.1.0%2"
		"2%7D%2C%22sequence_number%22%3A1%2C%22groups%22%3A%7B%7D%2C%22group_properties%22%3A%7B%7D%2C%22user_agent%22%3A%22Mozilla%2F5.0%20%28Windows%20NT%2010.0%3B%20Win64%3B%20x64%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F88.0.4324.182%20Safari%2F537.36%22%7D%5D&upload_time=1613932892431&v=2", 
		EXTRARES, 
		"Url=https://px.ads.linkedin.com/collect?v=2&fmt=js&pid=1320713&time=1613932893171&url=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021%26execution%3De1s1&cookiesTest=true", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		LAST);

	web_add_auto_header("Sec-Fetch-Mode", 
		"navigate");

	web_add_auto_header("Sec-Fetch-Dest", 
		"iframe");

	web_add_header("Upgrade-Insecure-Requests", 
		"1");

	web_url("iframeStorage-5.0.0.html", 
		"URL=https://config1.veinteractive.com/scripts/shared/iframeStorage-5.0.0.html?iframeid=ve-storage-iframe&journeyCode=d9d2b911-a3a0-4fa4-8f93-08084164b50a&journeyId=4687", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t118.inf", 
		"Mode=HTML", 
		LAST);

	web_add_cookie("stc115172=tsa:1613932810286.445323167.50012636.5997666628335889.:20210221191133|env:1%7C20210324184010%7C20210221191133%7C2%7C1047195:20220221184133|uid:1613932810285.1674410045.7457108.115172.428744288.:20220221184133|srchist:1047195%3A1%3A20210324184010:20220221184133; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("mf_recordingRules=W1siYmFzZSIsIioiLDI1LHRydWVdLFsic3RhcnRzV2l0aCIsIi9keCIsMTAwXV0=; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("mf_226ff167-994e-4acb-85f6-1678320b3aad=|.46435224.1613932822942$.3454984710.1613932893362|1613932893498||0|||0|17.37|58.53512; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("mbox=session#acfdcd4c076546849d13a0a9b5f1f504#1613934754|PC#acfdcd4c076546849d13a0a9b5f1f504.37_0#1677177605; DOMAIN=dxbooking.etihad.com");

	web_add_auto_header("Sec-Fetch-Dest", 
		"empty");

	web_add_auto_header("Sec-Fetch-Mode", 
		"cors");

	web_add_auto_header("Sec-Fetch-Site", 
		"same-origin");

	web_url("passengerConfiguration.json", 
		"URL=https://dxbooking.etihad.com/dx/EYDX/4.5.60-151.MMCOPY4.5.60-150/data/features/passengerConfiguration.json", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/dx/EYDX/", 
		"Snapshot=t119.inf", 
		"Mode=HTML", 
		LAST);

	web_add_cookie("AWSALB=HEz28MFCxb04LH7mzruk2JKqLKjceJOMGps7cmNwXGIfZW1egCXO0Szkv+8T1r2Afb0i2QAGEUoYmoJJbSL290rHwSnf38GaWMAG/K4fVBShBOExaMM876sDeDDl; DOMAIN=dxbooking.etihad.com");

	web_add_cookie("AWSALBCORS=HEz28MFCxb04LH7mzruk2JKqLKjceJOMGps7cmNwXGIfZW1egCXO0Szkv+8T1r2Afb0i2QAGEUoYmoJJbSL290rHwSnf38GaWMAG/K4fVBShBOExaMM876sDeDDl; DOMAIN=dxbooking.etihad.com");

	web_url("countries-mapping.json", 
		"URL=https://dxbooking.etihad.com/dx/EYDX/4.5.60-151.MMCOPY4.5.60-150/data/features/countries-mapping.json", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/dx/EYDX/", 
		"Snapshot=t120.inf", 
		"Mode=HTML", 
		LAST);

	web_add_cookie("amp_4cf3bd=ELJFL_0mHM4Cy3LlBTlmwf.Y2tsZmh6cTNoMDB0NWhpc2d3b3I2cjcyNg==..1ev2ub488.1ev2ub48u.3.2.5; DOMAIN=dc.etihad.com");

	web_add_cookie("stc115172=tsa:1613932810286.445323167.50012636.5997666628335889.:20210221191133|env:1%7C20210324184010%7C20210221191133%7C2%7C1047195:20220221184133|uid:1613932810285.1674410045.7457108.115172.428744288.:20220221184133|srchist:1047195%3A1%3A20210324184010:20220221184133; DOMAIN=dc.etihad.com");

	web_add_auto_header("Sec-Fetch-Site", 
		"same-site");

	web_add_header("Conversation-ID", 
		"cklfhzq3h00t5hisgwor6r726");

	web_add_auto_header("Origin", 
		"https://dxbooking.etihad.com");

//	web_custom_request("search_2", 
//		"URL=https://dc.etihad.com/v4.3/ssw/products/air/search?execution=e1s1&jipcc=EYDX", 
//		"Method=POST", 
//		"Resource=0", 
//		"RecContentType=application/json", 
//		"Referer=https://dxbooking.etihad.com/", 
//		"Snapshot=t121.inf", 
//		"Mode=HTML", 
//		"EncType=application/json", 
//		"Body={\"cabinClass\":\"Economy\",\"awardBooking\":\"false\",\"promoCodes\":[],\"searchType\":\"BRANDED\",\"itineraryParts\":[{\"from\":{\"useNearbyLocations\":false,\"code\":\"AUH\"},\"to\":{\"useNearbyLocations\":false,\"code\":\"BLR\"},\"when\":{\"date\":\"2021-04-30\"}}],\"passengers\":{\"ADT\":1}}", 
//		LAST);

	web_add_auto_header("Sec-Fetch-Site", 
		"cross-site");

	web_custom_request("api.amplitude.com_2", 
		"URL=https://api.amplitude.com/", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t122.inf", 
		"Mode=HTML", 
		"EncType=application/x-www-form-urlencoded; charset=UTF-8", 
		"Body=checksum=0d894f0378608400eb94d65304df7122&client=4cf3bd807cf1e9ef73f4b82bce06478a&e="
		"%5B%7B%22device_id%22%3A%22ELJFL_0mHM4Cy3LlBTlmwf%22%2C%22user_id%22%3A%22cklfhzq3h00t5hisgwor6r726%22%2C%22timestamp%22%3A1613932892440%2C%22event_id%22%3A2%2C%22session_id%22%3A1613932892424%2C%22event_type%22%3A%22%24identify%22%2C%22version_name%22%3A%224.5%22%2C%22platform%22%3A%22Web%22%2C%22os_name%22%3A%22Chrome%22%2C%22os_version%22%3A%2288%22%2C%22device_model%22%3A%22Windows%22%2C%22device_manufacturer%22%3Anull%2C%22language%22%3A%22en-US%22%2C%22carrier%22%3Anull%2C%22api_properties%2"
		"2%3A%7B%7D%2C%22event_properties%22%3A%7B%7D%2C%22user_properties%22%3A%7B%22%24set%22%3A%7B%22Release%20Bundle%20Version%22%3A%224.5.60-151.MMCOPY4.5.60-150%7C0%7C%22%2C%22Airline%20Name%22%3A%22Etihad%20%28EY%29%22%2C%22Conversation%20ID%22%3A%22cklfhzq3h00t5hisgwor6r726%22%2C%22Hostname%22%3A%22dxbooking.etihad.com%22%2C%22User%20Agent%22%3A%22Mozilla%2F5.0%20%28Windows%20NT%2010.0%3B%20Win64%3B%20x64%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F88.0.4324.182%20Safari%2F"
		"537.36%22%2C%22GTM%20Version%22%3A%2290%22%7D%7D%2C%22uuid%22%3A%220eda2613-d26c-4607-906d-fe15a3dd8201%22%2C%22library%22%3A%7B%22name%22%3A%22amplitude-js%22%2C%22version%22%3A%227.1.0%22%7D%2C%22sequence_number%22%3A2%2C%22groups%22%3A%7B%7D%2C%22group_properties%22%3A%7B%7D%2C%22user_agent%22%3A%22Mozilla%2F5.0%20%28Windows%20NT%2010.0%3B%20Win64%3B%20x64%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F88.0.4324.182%20Safari%2F537.36%22%7D%2C%7B%22device_id%22%3A%22ELJFL_0"
		"mHM4Cy3LlBTlmwf%22%2C%22user_id%22%3A%22cklfhzq3h00t5hisgwor6r726%22%2C%22timestamp%22%3A1613932892442%2C%22event_id%22%3A1%2C%22session_id%22%3A1613932892424%2C%22event_type%22%3A%22Start%20Page%20Reload%22%2C%22version_name%22%3A%224.5%22%2C%22platform%22%3A%22Web%22%2C%22os_name%22%3A%22Chrome%22%2C%22os_version%22%3A%2288%22%2C%22device_model%22%3A%22Windows%22%2C%22device_manufacturer%22%3Anull%2C%22language%22%3A%22en-US%22%2C%22carrier%22%3Anull%2C%22api_properties%22%3A%7B%7D%2C%22event_pr"
		"operties%22%3A%7B%22Entry%20Page%22%3A%22AIR_SELECT_PAGE%22%2C%22Flow%20Type%22%3A%22Booking%22%7D%2C%22user_properties%22%3A%7B%7D%2C%22uuid%22%3A%229f8b8dc7-2a83-403f-b9ac-6cc4af199cd0%22%2C%22library%22%3A%7B%22name%22%3A%22amplitude-js%22%2C%22version%22%3A%227.1.0%22%7D%2C%22sequence_number%22%3A3%2C%22groups%22%3A%7B%7D%2C%22group_properties%22%3A%7B%7D%2C%22user_agent%22%3A%22Mozilla%2F5.0%20%28Windows%20NT%2010.0%3B%20Win64%3B%20x64%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29"
		"%20Chrome%2F88.0.4324.182%20Safari%2F537.36%22%7D%2C%7B%22device_id%22%3A%22ELJFL_0mHM4Cy3LlBTlmwf%22%2C%22user_id%22%3A%22cklfhzq3h00t5hisgwor6r726%22%2C%22timestamp%22%3A1613932892444%2C%22event_id%22%3A2%2C%22session_id%22%3A1613932892424%2C%22event_type%22%3A%22Select%20Language%22%2C%22version_name%22%3A%224.5%22%2C%22platform%22%3A%22Web%22%2C%22os_name%22%3A%22Chrome%22%2C%22os_version%22%3A%2288%22%2C%22device_model%22%3A%22Windows%22%2C%22device_manufacturer%22%3Anull%2C%22language%22%3A%"
		"22en-US%22%2C%22carrier%22%3Anull%2C%22api_properties%22%3A%7B%7D%2C%22event_properties%22%3A%7B%22Storefront%22%3A%22EYDX%22%2C%22Flow%20Type%22%3A%22Booking%22%2C%22Flow%20Category%22%3A%22Revenue%22%2C%22Page%20Name%22%3A%22AIR_SELECT_PAGE%22%2C%22Selected%20Language%22%3A%22en-GB%22%7D%2C%22user_properties%22%3A%7B%7D%2C%22uuid%22%3A%220f500233-455b-4bbe-a377-5d6dfd60d36a%22%2C%22library%22%3A%7B%22name%22%3A%22amplitude-js%22%2C%22version%22%3A%227.1.0%22%7D%2C%22sequence_number%22%3A4%2C%22g"
		"roups%22%3A%7B%7D%2C%22group_properties%22%3A%7B%7D%2C%22user_agent%22%3A%22Mozilla%2F5.0%20%28Windows%20NT%2010.0%3B%20Win64%3B%20x64%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F88.0.4324.182%20Safari%2F537.36%22%7D%2C%7B%22device_id%22%3A%22ELJFL_0mHM4Cy3LlBTlmwf%22%2C%22user_id%22%3A%22cklfhzq3h00t5hisgwor6r726%22%2C%22timestamp%22%3A1613932892446%2C%22event_id%22%3A3%2C%22session_id%22%3A1613932892424%2C%22event_type%22%3A%22View%20Page%22%2C%22version_name%22%3A%224.5"
		"%22%2C%22platform%22%3A%22Web%22%2C%22os_name%22%3A%22Chrome%22%2C%22os_version%22%3A%2288%22%2C%22device_model%22%3A%22Windows%22%2C%22device_manufacturer%22%3Anull%2C%22language%22%3A%22en-US%22%2C%22carrier%22%3Anull%2C%22api_properties%22%3A%7B%7D%2C%22event_properties%22%3A%7B%22Page%20Name%22%3A%22AIR_SELECT_PAGE%22%2C%22Flow%20Type%22%3A%22Booking%22%2C%22Flow%20Category%22%3A%22Revenue%22%2C%22Storefront%22%3A%22EYDX%22%2C%22Page%20URI%22%3A%22%2Fdx%2FEYDX%2F%23%2Fflight-selection%3Fjourne"
		"yType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021%22%7D%2C%22user_properties%22%3A%7B%7D%2C%22uuid%22%3A%225e1914a9-b8ee-4714-ac5a-596093d9c07c%22%2C%22library%22%3A%7B%22name%22%3A%22amplitude-js%22%2C%22version%22%3A%227.1.0%22%7D%2C%22sequence_number%22%3A5%2C%22groups%22%3A%7B%7D%2C%22group_properties%22%3A%7B%7D%2C%22user_agent%22%3A%22Mozilla%2F5.0%20%28Windows%20NT%2010.0%3B%20Win64%3B%20x64%29%20AppleWebKit%2F537.36%20%28"
		"KHTML%2C%20like%20Gecko%29%20Chrome%2F88.0.4324.182%20Safari%2F537.36%22%7D%5D&upload_time=1613932894170&v=2", 
		LAST);

	web_url("D9D2B911-A3A0-4FA4-8F93-08084164B50A", 
		"URL=https://cookiee1.veinteractive.com/api/Set/D9D2B911-A3A0-4FA4-8F93-08084164B50A?ifs=false&offset=-120&referrer=dxbooking.etihad.com&status=-2&ttl=0&uid=&version=5.0.0", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t123.inf", 
		"Mode=HTML", 
		LAST);

	web_url("appsmanagerinit", 
		"URL=https://sessionapi.veinteractive.com/api/appsmanagerinit?isCookieEnabled=true&timeToLive=60&captureVersion=5.0&journeyCode=D9D2B911-A3A0-4FA4-8F93-08084164B50A&landingPage=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021%26execution%3De1s1&offset=-120&referrerDomain=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F&status=-2&uid=True&userAgent="
		"Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20Win64%3B%20x64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F88.0.4324.182%20Safari%2F537.36", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t124.inf", 
		"Mode=HTML", 
		EXTRARES, 
		"Url=https://a.volvelle.tech/ul_cb/sync?source=ve&redirect=//cookiee1.veinteractive.com/api/SyncCookie?clientName%3Dvolvelle%26journeyCode%3DD9D2B911-A3A0-4FA4-8F93-08084164B50A%26version%3D5.0.0%26referrer%3Ddxbooking.etihad.com%26offset%3D-120%26userId=", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		LAST);

	web_custom_request("api.amplitude.com_3", 
		"URL=https://api.amplitude.com/", 
		"Method=POST", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t125.inf", 
		"Mode=HTML", 
		"EncType=application/x-www-form-urlencoded; charset=UTF-8", 
		"Body=checksum=001a7c732e07f8daa3c765c984a3c410&client=4cf3bd807cf1e9ef73f4b82bce06478a&e="
		"%5B%7B%22device_id%22%3A%22ELJFL_0mHM4Cy3LlBTlmwf%22%2C%22user_id%22%3A%22cklfhzq3h00t5hisgwor6r726%22%2C%22timestamp%22%3A1613932898244%2C%22event_id%22%3A4%2C%22session_id%22%3A1613932892424%2C%22event_type%22%3A%22Search%20Flights%22%2C%22version_name%22%3A%224.5%22%2C%22platform%22%3A%22Web%22%2C%22os_name%22%3A%22Chrome%22%2C%22os_version%22%3A%2288%22%2C%22device_model%22%3A%22Windows%22%2C%22device_manufacturer%22%3Anull%2C%22language%22%3A%22en-US%22%2C%22carrier%22%3Anull%2C%22api_propert"
		"ies%22%3A%7B%7D%2C%22event_properties%22%3A%7B%22Storefront%22%3A%22EYDX%22%2C%22Flow%20Type%22%3A%22Booking%22%2C%22Flow%20Category%22%3A%22Revenue%22%2C%22Page%20Name%22%3A%22AIR_SELECT_PAGE%22%2C%22Search%20Cabin%20Class%22%3A%22Economy%22%2C%22Site%20Currency%22%3A%22%22%2C%22Search%20Trip%20Dates%22%3A%5B%2230-Apr-2021%22%5D%2C%22Search%20Type%22%3A%22One%20Way%22%2C%22Search%20Advanced%20Purchase%20Window%22%3A67%2C%22Search%20First%20Departure%20Date%22%3A%2230-Apr-2021%22%2C%22Search%20Fir"
		"st%20Departure%20Day%20of%20Week%22%3A%22Friday%22%2C%22Search%20OD%20Pairs%22%3A%5B%22AUH%3ABLR%22%5D%2C%22Search%20Adult%20Passengers%22%3A1%2C%22Search%20Child%20Passengers%22%3A0%2C%22Search%20Infant%20Passengers%22%3A0%2C%22Search%20Total%20Passengers%22%3A1%7D%2C%22user_properties%22%3A%7B%7D%2C%22uuid%22%3A%2214c80812-402a-4645-acaf-ed8b84ea5786%22%2C%22library%22%3A%7B%22name%22%3A%22amplitude-js%22%2C%22version%22%3A%227.1.0%22%7D%2C%22sequence_number%22%3A6%2C%22groups%22%3A%7B%7D%2C%22g"
		"roup_properties%22%3A%7B%7D%2C%22user_agent%22%3A%22Mozilla%2F5.0%20%28Windows%20NT%2010.0%3B%20Win64%3B%20x64%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F88.0.4324.182%20Safari%2F537.36%22%7D%5D&upload_time=1613932898245&v=2", 
		LAST);

	web_revert_auto_header("sec-ch-ua");

	web_revert_auto_header("sec-ch-ua-mobile");

	web_add_header("Access-Control-Request-Headers", 
		"content-type");

	web_add_header("Access-Control-Request-Method", 
		"POST");

	web_custom_request("FormMappings", 
		"URL=https://dtrc.veinteractive.com/FormMappings", 
		"Method=OPTIONS", 
		"Resource=0", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t126.inf", 
		"Mode=HTML", 
		LAST);

	web_add_auto_header("sec-ch-ua", 
		"\"Chromium\";v=\"88\", \"Google Chrome\";v=\"88\", \";Not A Brand\";v=\"99\"");

	web_add_auto_header("sec-ch-ua-mobile", 
		"?0");

	web_custom_request("FormMappings_2", 
		"URL=https://dtrc.veinteractive.com/FormMappings", 
		"Method=POST", 
		"Resource=0", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t127.inf", 
		"Mode=HTML", 
		"EncType=application/json;charset=UTF-8", 
		"Body={\"captureUrl\":\"dxbooking.etihad.com/dx/EYDX/?/flight-selection?journeyType=one-way&ADT=1&CHD=0&INF=0&class=Economy&origin=AUH&destination=BLR&date=04-30-2021&execution=e1s1\",\"capturedDataContext\":{\"pageIdentification\":{\"pageType\":0,\"inferred\":{},\"configuredForms\":[1],\"formMappingInfo\":{\"1\":{\"29709\":{\"type\":1,\"domEventId\":6},\"45365\":{\"type\":1,\"domEventId\":6},\"54992\":{\"type\":1,\"domEventId\":6},\"83081\":{\"type\":1,\"domEventId\":6},\"125052\":{\"type\":1,\""
		"domEventId\":3},\"441759\":{\"type\":1,\"domEventId\":3},\"456933\":{\"type\":1,\"domEventId\":6},\"656083\":{\"type\":1,\"domEventId\":3},\"675855\":{\"type\":1,\"domEventId\":6},\"675856\":{\"type\":1,\"domEventId\":3},\"680592\":{\"type\":1,\"domEventId\":6},\"684286\":{\"type\":1,\"domEventId\":6}}},\"saleFlags\":{\"inferred\":false,\"pixel\":false,\"identified\":false,\"total\":\"\"}},\"isPageView\":true,\"url\":{\"location\":\"https://dxbooking.etihad.com/dx/EYDX/#/flight-selection?"
		"journeyType=one-way&ADT=1&CHD=0&INF=0&class=Economy&origin=AUH&destination=BLR&date=04-30-2021&execution=e1s1\",\"referrer\":\"https://dxbooking.etihad.com/dx/EYDX/\"},\"jump\":{\"pageJump\":0,\"pageJumpIn\":0}},\"customerId\":3614,\"formMappings\":{\"29709\":\"en_GB\",\"45365\":\"economy\",\"54992\":\"https://dxbooking.etihad.com/dx/EYDX/#/flight-selection?&journeyType=one-way&origin=AUH&destination=BLR&date=04-30-2021&class=Economy&alternativeLandingPage=true&ADT=1&CHD=0&INF=0&lang=en&"
		"referrerCode=affveinter\",\"83081\":\"veConnect\",\"125052\":\"21/02/2021\",\"441759\":\"veConnect\",\"456933\":\"AUH BLR Select Flight\",\"656083\":\"1613932894124\",\"675855\":\"https://dxbooking.etihad.com/dx/EYDX/#/flight-selection?journeyType=one-way&ADT=1&CHD=0&INF=0&class=Economy&origin=AUH&destination=BLR&date=04-30-2021&execution=e1s1\",\"675856\":\"en\",\"680592\":\"AUH\",\"684286\":\"AUH BLR\"},\"ifsStatus\":{\"audit\":{\"isCookieAvailable\":false,\"isStorageAvailable\":false,\"message"
		"\":\"Failed to read the 'localStorage' property from 'Window': Access is denied for this document.\"},\"state\":{\"veCookieId\":\"\",\"veCookieStatus\":-2}},\"journeyCode\":\"D9D2B911-A3A0-4FA4-8F93-08084164B50A\",\"pageId\":\"1613932896834\",\"sessionId\":\"e9039aa0-4c8a-4e4e-8cad-c70670988702\",\"translatorDetected\":false,\"type\":\"pageViewed\",\"version\":\"v001\"}", 
		LAST);

	web_add_cookie("bm_sz=325B51BE6EBB36655AF63A49E555E7A4~YAAQL1BzaL7L87V3AQAAuMrkxQrBo8SLQvRDmDy8XF0hOtBs87Uff/VgXpyC3bv0YWm5bzXEaOBy9RlToErhEjXonUt9goIDmAuj+muEPfp8jY+JdnWQBGE9Q5kexYUQgleioXJlG8HWUbV8wkxTU+t8vDV4wGqkl2njtLHGwbVlY0rN4NHIxp6sNRBX+H/y; DOMAIN=smetrics.etihad.com");

	web_add_cookie("ak_bmsc=A9F9127680F343DD461B5FC9E5463D586873502F241C000029A9326087F17029~plaxa/Cp4zd3qSzpJLmP3uUIDkoliTRLX9T/DrDUhlnItwYqt9u3k9Hh1BF8UvHnXMdOVJq82GzdbKO3ZdVDKsmiXdCCuMmlnMDQNzHxEo1F+BHIun7EndxTiap2y5ZcxFKlbYztGdqGWOxbwEMuuPICokVTkBPlaTfEpOlltpzjL0aoiqxgno26poQFwfkHrbf3I8nIjXm+GpdJvJ0xVfiq+S46H9prXHgR+fDZ1GEkWA2jqa2FnznZhP/xvhaCzU; DOMAIN=smetrics.etihad.com");

	web_add_cookie("_abck=47ACA25003F1D5F4A04DC7C4F295531C~0~YAAQL1BzaN3L87V3AQAA0/3kxQW9oNCNcjvSyl2JXnugs5UOE6EOOE5wliBlX7RRNm+VO/S74BMWAqam3wPqwIhVwBiccacb2RnT42cUGU4WYsFhafZN7ApLyw/KXDQWUm7boIxZ2Zq0ZRjC/S4AC1DYzSW3vSeitmW64KL6YfmBXwuytDYG7FabecL+hUgPCglbXVS72+IZ6tZu1FKNkMRs3uEsAGHpc20oBuKd6YDY0YAkOTqr/zoszDR6Xyl1A7+5p5goE7Z3r4CJTdLyV+7RTfiHmxgNOKjT4c2/DXMzM1rRFszr00EAZV4quNqVmMtExf09U7oxLfsuKkzvDrSvPUOsgg==~-1~||-1||~-1; DOMAIN=smetrics.etihad.com");

	web_add_cookie("s_visit=1; DOMAIN=smetrics.etihad.com");

	web_add_cookie("_sctr=1|1613858400000; DOMAIN=smetrics.etihad.com");

	web_add_cookie("AAMC_etihadairways_0=REGION%7C6; DOMAIN=smetrics.etihad.com");

	web_add_cookie("bd4ti=Sl68_InJJTao.1613932824190; DOMAIN=smetrics.etihad.com");

	web_add_cookie("SSW=ReturnUrl=https://www.etihad.com/en-us/book&IsResidenceSelected=false&VIPRoute=false; DOMAIN=smetrics.etihad.com");

	web_add_cookie("SearchPanelConfig=SearchPanelConfig={\"SearchedSource\":\"Abu Dhabi (AUH)\",\"SearchedDest\":\"Bengaluru (BLR)\",\"SrcCntryCode\":\"AE\",\"DestCntryCode\":\"IN\",\"LandingPage\":\"https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-20en\",\"BookingType\":1,\"HotelsEnabled\":\"False\",\"CarsEnabled\":\"False\",\"SiteEdition\":\"en-us\",\"hdnTT\":"
		"\"\",\"SelectedPaxDetails\":{\"A\":\"1\",\"C\":\"0\",\"I\":\"0\",\"Y\":\"0\"},\"TrvlCls\":{\"ECO\":{\"MaxPax\":9,\"MinPax\":1},\"BSINSS\":{\"MaxPax\":9,\"MinPax\":1},\"FIRST\":{\"MaxPax\":9,\"MinPax\":1},\"VFIRST\":{\"MaxPax\":2,\"MinPax\":1,\"VIPSaleStrtDt\":\"27/06/2014\",\"VIPEnLoc\":\"SYD,LHR,JFK,CDG,ICN\"}},\"Pax\":{\"limitIndivPax\":\"False\",\"MultOfTwo\":\"False\",\"A\":{\"MaxPax\":9,\"MinPax\":1,\"MaxAdlts\":9,\"MinAdlts\":1,\"GetAOpt\":9},\"I\":{\"GetIOpt\":2},\"C\":{\"MinC\":0,\"MaxC\""
		":0,\"GetCOpt\":9}}}; DOMAIN=smetrics.etihad.com");

	web_add_cookie("reese84=3:iI3nZ1r+2u3bISVyzsbgxA==:AmK58rBu/FCQBrBxaVytxWGz5gsH6dyWwo4xK1FsFmnczi6wAUuexgczYoMXqFOOQ61Te+JWFAJLCUB64OUPAq2X5H7XFF8L9RNw2fv+H0w0uYlqncHEa+x92MOeWPym3CDm5xgQwY9PJWtzEtsRic0VKaABlodtmwuqW7KN/zqnGee9mAUQgKSOg3Q4m1obXL4YLWRbY8n7UwPdegprt2+BQ0xYp5Dz0ig7DGnpxgWmgLCPMksfRYqUovC1oQTr8Nu00BGePE4hpvq+JugeIEfQcPm1R79ZftAKxoEUvwbCS3Z6dLaJwcv8wu5k/Uv0C/Y61GPq0hhOJ1H/3Cz53IEZkgRXTBMsRfhCuw0Jn6YsrBfIw3p6A1zel0irzcE8OjqlVX8TdlYavVovKAb5EsB6uieXUQ14d5GixJ8WyOA="
		":GYld9KWDH5g6hX7UdiBhhpNfPEhX8xFqBaGkljQqZ+w=; DOMAIN=smetrics.etihad.com");

	web_add_cookie("visid_incap_2042951=+a45m09xTc2QjCqgL2pSPoSpMmAAAAAAQUIPAAAAAABB8074cy8jBqwFwU9Xjxak; DOMAIN=smetrics.etihad.com");

	web_add_cookie("incap_ses_1168_2042951=StLsZ2OaFzmBFqFQE5I1EISpMmAAAAAAhPiIugo5f9yNcH97igeUYg==; DOMAIN=smetrics.etihad.com");

	web_add_cookie("_ga_G21M2QMYH2=GS1.1.1613932802.1.1.1613932890.0; DOMAIN=smetrics.etihad.com");

	web_add_cookie("_ga=GA1.2.686669418.1613932802; DOMAIN=smetrics.etihad.com");

	web_add_cookie("stc115172=tsa:1613932810286.445323167.50012636.5997666628335889.:20210221191133|env:1%7C20210324184010%7C20210221191133%7C2%7C1047195:20220221184133|uid:1613932810285.1674410045.7457108.115172.428744288.:20220221184133|srchist:1047195%3A1%3A20210324184010:20220221184133; DOMAIN=smetrics.etihad.com");

	web_add_cookie("mf_226ff167-994e-4acb-85f6-1678320b3aad=|.46435224.1613932822942$.3454984710.1613932893362|1613932893498||0|||0|17.37|58.53512; DOMAIN=smetrics.etihad.com");

	web_add_cookie("mbox=session#acfdcd4c076546849d13a0a9b5f1f504#1613934754|PC#acfdcd4c076546849d13a0a9b5f1f504.37_0#1677177605; DOMAIN=smetrics.etihad.com");

	web_add_cookie("amp_4cf3bd=ELJFL_0mHM4Cy3LlBTlmwf.Y2tsZmh6cTNoMDB0NWhpc2d3b3I2cjcyNg==..1ev2ub488.1ev2ub9u4.4.2.6; DOMAIN=smetrics.etihad.com");

	web_add_cookie("s_pageName_cookie=eydx%3Aflight-selection; DOMAIN=smetrics.etihad.com");

	web_add_cookie("s_srv=dx%3Abundle%3A4.5.60-151.MMCOPY4.5.60-150-build_no%3A0; DOMAIN=smetrics.etihad.com");

	web_add_cookie("s_ppvl=book%2C26%2C15%2C1258%2C1320%2C725%2Cundefined%2Cundefined%2C1%2CP; DOMAIN=smetrics.etihad.com");

	web_add_cookie("s_ppv=eydx%253Aflight-selection%2C18%2C18%2C725%2C1320%2C725%2Cundefined%2Cundefined%2C1%2CP; DOMAIN=smetrics.etihad.com");

	web_add_cookie("s_ptc=pt.rdr%240.01%5E%5Ept.apc%240.00%5E%5Ept.dns%240.00%5E%5Ept.tcp%240.00%5E%5Ept.req%240.49%5E%5Ept.rsp%240.01%5E%5Ept.prc%243.97%5E%5Ept.onl%240.00%5E%5Ept.tot%244.49%5E%5Ept.pfi%241; DOMAIN=smetrics.etihad.com");

	web_add_cookie("s_sq=%5B%5BB%5D%5D; DOMAIN=smetrics.etihad.com");

	web_add_auto_header("Sec-Fetch-Site", 
		"same-site");

	web_url("IN", 
		"URL=https://webmodule.etihad.com/v5/brandedfare.svc/AUH/BLR/AE/IN", 
		"Resource=0", 
		"RecContentType=application/json", 
		"Referer=https://dxbooking.etihad.com/", 
		"Snapshot=t128.inf", 
		"Mode=HTML", 
		EXTRARES, 
		"Url=https://assets.adobedtm.com/8aea536f4a27/88ed4f88d8f6/b952b1e8a447/RCfc6163c4bb5b4350ab5acf74053f9164-source.min.js", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		"Url=https://smetrics.etihad.com/b/ss/etihaddotcomprod/10/JS-2.17.0-LBQ1/s85423942724336?AQB=1&ndh=1&pf=1&callback=s_c_il[1].doPostbacks&et=1&t=21%2F1%2F2021%2020%3A41%3A43%200%20-120&d.&nsid=0&jsonv=1&.d&sdid=07527C11692ADC04-1DC54A1454C83E02&mid=26185866700827066552622528666916780474&aamlh=6&ce=UTF-8&ns=etihadairways&cdp=2&pageName=eydx%3Aflight-selection&g="
		"https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F%23%2Fflight-selection%3FjourneyType%3Done-way%26ADT%3D1%26CHD%3D0%26INF%3D0%26class%3DEconomy%26origin%3DAUH%26destination%3DBLR%26date%3D04-30-2021%26execution%3De1s1&r=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F&cc=AED&server=dx%3Abundle%3A4.5.60-151.MMCOPY4.5.60-150-build_no%3A0&events=event1%2Cevent21%3D18&aamb=j8Odv6LonN4r3an7LhD3WZrU1bUpAkFkkiY1ncBR96t2PTI&c1=D%3Dv1&v1=en-GB&c3=D%3Dv3&v3=economy%3Aauh%3Ablr&c4=D%3Dv4&v4=30%2F04%2F2021&"
		"c5=D%3Dv5&v5=21%2F02%2F2021%3A30%2F04%2F2021&c6=D%3Dv6&v6=1adt%3A0chd%3A0inf&c7=D%3Dv7&v7=booking&c8=D%3Dv8&v13=AED&c15=D%3Dv15&v15=en-GB%3Aeydx%3Aflight-selection&c16=8%3A41%20PM%7CSunday&v16=8%3A41%20PM%7CSunday&c17=D%3Dv17&v17=book&c20=D%3Dv20&v20=%7C%7C&c22=D%3Dv22&v22=en-us&c29=D%3Dv29&v29=D%3Dg&v32=67&v49=mozilla%2F5.0%20%28windows%20nt%2010.0%3B%20win64%3B%20x64%29%20applewebkit%2F537.36%20%28khtml%2C%20like%20gecko%29%20chrome%2F88.0.4324.182%20safari%2F537.36&v54=1613932889578&v58="
		"D%3DpageName&v59=686669418.1613932802%7C&c60=D%3Dv60&v60=prod&c64=D%3Dv64&c67=D%3Dv67&v67=one-way&v72=18&c73=D%3Dv73&v73=bundle%3A4.5.60-151.MMCOPY4.5.60-150-build_no%3A0&v89=D%3Dmid&v93=D%3Dv67&v97=26&v127=book%3Aetihad-adobe-aem-hosting&v149=dxbooking.etihad.com&v150=launch&v199=False&v232=dxData-object%3AdataLayer-object&v240=pageload&v247=https%3A%2F%2Fdxbooking.etihad.com%2Fdx%2FEYDX%2F&c.&a.&activitymap.&page=book&link=Search%20flights&region=flightsearch&pageIDType=1&.activitymap&.a&.c&pid="
		"book&pidt=1&oid=functionlt%28%29%7B%7D&oidt=2&ot=BUTTON&s=1440x900&c=24&j=1.6&v=N&k=Y&bw=1320&bh=725&mcorgid=15BD401053DAEC4A0A490D4C%40AdobeOrg&AQE=1", "Referer=https://dxbooking.etihad.com/", ENDITEM, 
		LAST);

	lr_end_transaction("Vu_SearchFlight_transaction",LR_AUTO);

	return 0;
}