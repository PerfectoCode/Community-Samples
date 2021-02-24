/***  Adding Jquery min JS file  */
var script = document.createElement('script');
script.src = 'https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

/***
 * Function to initialize custom javascript.
 * @param eventsListener - Event listener object which will publish all actions from the application
 */
let flightData, tempFlightData;
let interlineOrigins, interlineDestinationsPerOrigin, isInterlineRedirect;
let isMobileApp;
let origins = ['AUH', 'ZVH', 'XNB'];
var initDXCustomJS = function (eventsListener) {
    eventsListener.subscribe('AIRLINE_INTERLINE_ROUTES', function (action) {
        isInterlineRedirect = action.interlineRoutes.isEnabled;
        interlineOrigins = action.interlineRoutes.origins;
        interlineDestinationsPerOrigin = action.interlineRoutes.destinationsPerOrigin;
    });

    eventsListener.subscribe('APP_LOAD_COMPLETE', function (action) {
        (function () {
            if (typeof NodeList.prototype.forEach === "function") return false;
            NodeList.prototype.forEach = Array.prototype.forEach;
        })();

        var targetUrl = "https://booking.etihad.com/SSW2010/EYEY/webqtrip.html?alternativeLandingPage=true&";
        var dxUrlParams = window.location.href.split('?')[1].split("&").map(function (p) {
            return p = p.split("="), this[p[0]] = p[1], this
        }.bind({}))[0];

        var commonMapping = {

            "origin": "origin",
            "destination": "destination",
            "journeyType": "journeySpan",
            "class": "cabinClass",
            "ADT": "numAdults",
            "CHD": "numChildren",
            "INF": "numInfants",
            "date": "departureDate",
            "promoCode": "promoCode",
            "awardBooking": "isAward",
            "date1": "returnDate",
            "locale": "lang"
        };

        var multiCityMapping = {
            "origin": "origin",
            "destination": "destination",
            "journeyType": "journeySpan",
            "class": "cabinClass",
            "ADT": "numAdults",
            "CHD": "numChildren",
            "INF": "numInfants",
            "date": "departureDate",
            "promoCode": "promoCode",
            "awardBooking": "isAward",
            "origin1": "origin2",
            "origin2": "origin3",
            "origin3": "origin4",
            "origin4": "origin5",
            "origin5": "origin6",
            "destination1": "destination2",
            "destination2": "destination3",
            "destination3": "destination4",
            "destination4": "destination5",
            "destination5": "destination6",
            "date1": "departureDate2",
            "date2": "departureDate3",
            "date3": "departureDate4",
            "date4": "departureDate5",
            "date5": "departureDate6",
            "locale": "lang"
        };

        var languageMapping = {
            "en-GB": "EN",
            "ar-AE": "AR",
            "zh-CN": "CN",
            "nl-NL": "NL",
            "fr-CA": "FR",
            "fr-FR": "FR",
            "de-DE": "DE",
            "el-GR": "GR",
            "it-IT": "IT",
            "ja-JP": "JA",
            "ko-KR": "KO",
            "pt-BR": "PT",
            "ru-RU": "RU",
            "es-ES": "ES",
            "th-TH": "TH",
            "tr-TR": "TR"
        };

        var metaSearchMapping = {
            "airlineCode": "mktAirline",
            "arrival": "arrival",
            "bookingClass": "bkgClass",
            "brandId": "brandId",
            "cabinClass": "cbnClass",
            "departure": "departure",
            "destination": "to",
            "equipment": "flight",
            "fareBasis": "fbcode",
            "flightNumber": "flight",
            "leg": "leg",
            "marriageGroup": "marriageGrp",
            "operatingAirlineCode": "oprAirline",
            "origin": "from",
            "journeyType": "journeySpan",
            "ADT": "numAdults",
            "CHD": "numChildren",
            "INF": "numInfants",
            "referrerCode": "referrerCode",
            "advertisedFare[amount]": "advertisedFare",
            "advertisedFare[currency]": "currency",
            "promoCode": "promoCode",
            "sswdmwa1": "sswdmwa1",
            "sswdmwa2": "sswdmwa2",
            "utm_medium": "utm_medium",
            "utm_source": "utm_source",
            "utm_campaign": "utm_campaign",
            "utm_term": "utm_term",
            "utm_content": "utm_content"
        }


        function dxToV12Params(urlParams, targetMapping) {

            var finalParams = {};
            var metaParamRegex = /ms\[[0-9]\]\[\w*\]/;
            Object.keys(urlParams).forEach(function (key) {
                if (targetMapping[key]) {
                    finalParams[targetMapping[key]] = processValue(key, urlParams[key])
                } else if (metaParamRegex.test(key)) {
                    var v12Format = key.replace("][", "].").substring(0, key.length - 1);
                    var dxKey = v12Format.split(".")[1];
                    var value = urlParams[key];
                    if (dxKey === 'arrival' || dxKey === 'departure') {
                        value = processValue(dxKey, urlParams[key])
                    }
                    if (dxKey && targetMapping[dxKey]) {
                        finalParams[v12Format.replace(dxKey, targetMapping[dxKey])] = value;
                    }
                }
            });

            return finalParams;
        }


        function processValue(key, value) {
            var returnVal = "";

            switch (key) {
                case (key.match(/^date/) || {}).input:
                    var splitDate = value.split('-');
                    returnVal = splitDate[2] + '-' + splitDate[0] + '-' + splitDate[1];
                    break;
                case 'locale':
                    returnVal = languageMapping[value] ? languageMapping[value] : 'EN';
                    break;
                case 'journeyType':
                    returnVal = (value === 'one-way') ? 'OW' : (value === 'round-trip' ? 'RT' : 'MC');
                    break;
                case 'class':
                    returnVal = value.toUpperCase();
                    break;
                case 'arrival':
                    returnVal = value.replace(':','');
                    break;
                case 'departure':
                    returnVal = value.replace(':','');
                    break;
                default:
                    returnVal = value;
            }
            return returnVal;
        }

        function redirectToV12() {

            var newParamsObj = {};
            if(window.location.hash.indexOf("#/meta-search")>=0){
                newParamsObj = dxToV12Params(dxUrlParams, metaSearchMapping);
                targetUrl += Object.keys(newParamsObj).map(function (key) {
                    return key + '=' + newParamsObj[key]
                }).join('&');
                window.location.href = targetUrl;
            }else{
                newParamsObj = dxToV12Params(dxUrlParams, dxUrlParams["journeyType"] === "multi-city" ? multiCityMapping : commonMapping);
                targetUrl += Object.keys(newParamsObj).map(function (key) {
                    return key + '=' + newParamsObj[key]
                }).join('&');
                window.location.href = targetUrl;
            }

        };

        if (dxUrlParams["promoCode"] && dxUrlParams["promoCode"].length > 0) {
           // redirectToV12();
        }

        if(dxUrlParams["source"] !== undefined && dxUrlParams["source"].length>0){
            isMobileApp = dxUrlParams["source"];
        }
    });

    eventsListener.subscribe('SEARCH_FLIGHTS_REQUEST', function (action) {
        const isAfopCodeExist = sessionStorage.getItem("DX_AfopCode");
        if (isAfopCodeExist) {
            sessionStorage.removeItem("DX_AfopCode");
        }
    });

    eventsListener.subscribe('SEARCH_FLIGHTS_RESPONDED', function (action) {
        const noFlightMessage = $("[data-translation='flightNotFound.title']").closest(".dxp-message") ? $("[data-translation='flightNotFound.title']").closest(".dxp-message").length : 0;
        if (noFlightMessage) {
            setTimeout(function () {
                $("[data-translation='flightNotFound.title']").closest(".dxp-message").hide();
            }, 5000);
        }
        // Build flight data
        buildFlightData(action.searchCriteria, action.flights, action.calendarRibbon, action.currency, action.direction);
        const flights = action.flights.toJS()[0];
        const cabinClass = action.searchCriteria.cabinClass;
        const itineraryPart = action.searchCriteria.itineraryParts[0];
        const departureDate = itineraryPart.date.format('YYYYMMDD');
        const calendarFare = action.calendarRibbon[0];
        const lowestFare = calendarFare !== undefined && calendarFare[departureDate].total[0];
        const calAvailable = calendarFare !== undefined && calendarFare[departureDate];
        const flightSize = action.flights.size;
        let displayCalRibbon = false;
        if (cabinClass === 'First' && flightSize !== 0 && calendarFare !== undefined && calAvailable.status === 'AVAILABLE') {
            flights.forEach(function (flight) {
                const cabins = Object.values(flight.cabins);

                for (var key in cabins) {
                    if (cabins.hasOwnProperty(key)) {
                        const cabin = cabins[key];
                        if (!cabin.sold) {
                            const total = cabin.total[0];
                            const flightTotal = total[0].amount;
                            if (lowestFare[0].amount === flightTotal) {
                                displayCalRibbon = true;
                            }
                        }
                    }
                }
            });
        }

        if (!displayCalRibbon && cabinClass === 'First') {
            $('.dx-flight-selection-ribbon').addClass('hide-price');
        }
        hideRibbonPrices(action.flights, action.direction, action.promocodeValid, action.warnings);
    });

    eventsListener.subscribe('CLEAR_FLIGHT_SELECTION', function (action) {
        const journeyType = action.flightsData.searchCriteria.journeyType;
        if (action.flightsData.currentFlow === 'b2c' && ((journeyType === 'round-trip' && action.direction === 1) || journeyType === 'one-way')) {
            hideRibbonPrices(action.flightsData.flights, action.direction, action.flightsData.promocodeValid, action.flightsData.warnings);
        }
    })

    eventsListener.subscribe('SELECT_FLIGHT', function (action) {
        // Script to do on select flight
        const selectedFlights = action.selectedFlights;
        const selectedFlightToJS = selectedFlights.toJS();
        var mismatchCount = 0;

        for (var key in selectedFlightToJS) {
            if (selectedFlightToJS.hasOwnProperty(key)) {
                const segments = selectedFlightToJS[key].segments;
                const firstCabinClass = segments[0].cabinClass;
                segments.forEach(function (segment) {
                    if (firstCabinClass !== segment.cabinClass) {
                        mismatchCount++;
                    }
                });
            }
        }

        if (mismatchCount > 0) {
            document.getElementById("mixed-cabin").style.display = "block";
        }
    });

    eventsListener.subscribe('CART_RESPONDED', function (action) {
        const url = window.location.href.split('?')[0];
        const urlSplit = url.split('/');
        const itinerary = action.results.itinerary;
        let itineraryParts = itinerary.itineraryParts;
        const pageName = urlSplit[urlSplit.length - 1];
        if (pageName === 'flight-selection') {

            var mismatchCount = 0;
            itineraryParts.forEach(function (itineraryPart) {
                const segments = itineraryPart.segments;
                const firstCabinClass = segments[0].cabinClass;
                segments.forEach(function (segment) {
                    if (firstCabinClass !== segment.cabinClass) {
                        mismatchCount++;
                    }
                });
            });
            if (mismatchCount > 0) {
                document.getElementById("mixed-cabin").style.display = "block";
            }
        }
        if (pageName === 'ancillaries') {
            const origin = itineraryParts[0].segments[0].origin;
            if($.inArray(origin,origins) !== -1) {
                document.getElementById("ancillaries-disclaimer").style.display = "block";
            } else {
                document.getElementById("ancillaries-disclaimer").style.display = "none";
            }
        }
    });

    eventsListener.subscribe('PAYMENT_QUERY_UPDATED', function (action) {
        const paymentQuery = action.paymentQuery[0];
        if (paymentQuery.paymentType === 'AFOP') {
            const afopCode = paymentQuery.afopCode;
            sessionStorage.setItem("DX_AfopCode", afopCode);
        }
    });

    eventsListener.subscribe('PROCESS_PAYMENT_RESPONDED', function (action) {
        if (action.confirmation !== undefined) {
            let pnrInfo = {
                "pnr": action.confirmation.pnr,
                "lastName": action.confirmation.passengers[0].passengerDetails.lastName,
                "status" : 200
            }
            let pnrStringfy = JSON.stringify(pnrInfo);
            purchaseCallBack(pnrStringfy)
        }
    });

    eventsListener.subscribe('PAYMENT_ERROR', function (action) {
        if (action.data.message !== undefined) {
            purchaseCallBack(action.data.message)
        }
    });

    eventsListener.subscribe('START_OVER', function () {
        isMobileApp && sessionErrorCallBack('Session Timeout')
    });

    eventsListener.subscribe('PNR_RESPONDED', function (action) {
        const identifier = action.pnrInfo.payments[0].identifier;
        if (identifier === null || identifier === undefined) {
            $('[data-translation="titles.paymentDetails"]').hide();
        }
        setTimeout(function () {
            fnHandleK3DomManupulation();
        }, 100);
    });
};

/***
 * Function that will be called on app destroy. You can do all clean up items here
 */
var destroyDxCustomJs = function () {
    // Script to do on app destroy
};

function hideRibbonPrices(flightsData, direction, promocodeValid, warnings) {
    let isDiscounted = false;
    const isPromoCodeApplied = !warnings.includes('PROMOTION_CODE_VALID_BUT_NO_DISCOUNTS_APPLIED');
    if (promocodeValid && isPromoCodeApplied) {
        const flights = flightsData.toJS()[direction];

        outerLoop:
            for (const i in flights) {
                const cabins = Object.values(flights[i].cabins || []);
                for (const j in cabins) {
                    const cabin = cabins[j];
                    const offers = Object.values(cabin.offers || []); // brands in the cabins
                    for (const k in offers) {
                        const brand = offers[k];
                        if (!brand.soldOut) {
                            const brandOffers = Object.values(brand.offers || []);
                            for (const l in brandOffers) {
                                const brandOffer = brandOffers[l];
                                isDiscounted = brandOffer.offerInformation.discounted;
                                if (isDiscounted) {
                                    break outerLoop;
                                }
                            }
                        }
                    }
                }
            }
        if (isDiscounted) {
            $('.dx-flight-selection-ribbon').addClass('hide-price');
            $('.dx-flight-selection-ribbon-item.selected').addClass('show-price');
        }
    }
}

function sessionErrorCallBack(errorMessage) {
    isMobileApp === 'amobile' && Android.showErrorToast(errorMessage);
    isMobileApp === 'imobile' && webkit.messageHandlers.SendTimeoutError.postMessage(errorMessage);
}

function purchaseCallBack(purchaseInfo) {
    isMobileApp === 'amobile' && Android.showToast(purchaseInfo);
    isMobileApp === 'imobile' && webkit.messageHandlers.paymentResponse.postMessage(purchaseInfo);
}

function isOriginDestinationAvail(originDestinationArray, originDestination) {
    for (let i = 0; i < originDestinationArray.length; i++) {
        if (originDestinationArray[i][0] == originDestination[0] && originDestinationArray[i][1] == originDestination[1]) {
            return true;
        }
    }
    return false;
}

function isInterlineOriginDestination(origin, destination) {
    const isInterlineOrigin = interlineOrigins.indexOf(origin);
    let isInterlineDestination;
    const destinationsPerOrigin = interlineDestinationsPerOrigin[origin];
    if (isInterlineOrigin !== -1 && destinationsPerOrigin !== undefined) {
        isInterlineDestination = destinationsPerOrigin.indexOf(destination);
        if (isInterlineDestination !== -1) {
            return true;
        }
        return false;
    }

}

function buildFlightData(searchCriteria, flights, calendarRibbon, currency, direction) {

    const journeyType = searchCriteria.journeyType;
    const cabinClass = searchCriteria.cabinClass;

    direction === 0 && initializeFlightData(currency, journeyType, cabinClass, flights.size);
    if (flights.size === 0) {
        flightData = tempFlightData;
        return;
    }


    let firstItineraryPart, lastItineraryPart, departureDate, returnDate;
    let departureCalendarFare, returnCalendarFare, lowestAvailableFareOutbound, lowestAvailableFareReturn;
    let flightResultsShownOutbound, flightResultsShownReturn, totalLowestFareRoundTrip, totalLowestFareOneWayTrip,
        calendarAvailable;
    flightData = direction === 0 && journeyType !== tempFlightData.journeyType ? '' : flightData;

    if (journeyType === 'one-way' || journeyType === 'round-trip') {
        firstItineraryPart = searchCriteria.itineraryParts[0];
        lastItineraryPart = searchCriteria.itineraryParts[1];
        departureDate = firstItineraryPart.date.format('YYYYMMDD');
        returnDate = lastItineraryPart === undefined ? '' : lastItineraryPart.date.format('YYYYMMDD');
        departureCalendarFare = calendarRibbon[0];
        returnCalendarFare = calendarRibbon[1];
        calendarAvailable = (departureCalendarFare !== undefined && departureCalendarFare[departureDate].status === 'UNAVAILABLE') || (returnCalendarFare !== undefined && returnCalendarFare[returnDate].status === 'UNAVAILABLE') ? false : true;
        lowestAvailableFareOutbound = calendarAvailable ? direction === 0 ? departureCalendarFare[departureDate].total[0][0].amount : tempFlightData.lowestAvailableFareOutbound : 0;
        lowestAvailableFareReturn = calendarAvailable ? returnCalendarFare === undefined ? 0 : returnCalendarFare[returnDate].total[0][0].amount : 0;
        flightResultsShownOutbound = calendarAvailable ? direction === 0 ? flights.toJS()[0].length : tempFlightData.flightResultsShownOutbound : 0;
        flightResultsShownReturn = calendarAvailable ? flights.toJS()[1] === undefined ? 0 : flights.toJS()[1].length : 0;
        totalLowestFareRoundTrip = direction === 1 ? Number(lowestAvailableFareOutbound) + Number(lowestAvailableFareReturn) : 0;
        totalLowestFareOneWayTrip = direction === 0 && journeyType === 'one-way' ? Number(lowestAvailableFareOutbound) : 0;


        tempFlightData = {
            "lowestAvailableFareCurrency": currency,
            "lowestAvailableFareOutbound": lowestAvailableFareOutbound,
            "lowestAvailableFareReturn": lowestAvailableFareReturn,
            "flightResultsShownOutbound": flightResultsShownOutbound,
            "flightResultsShownReturn": flightResultsShownReturn,
            "totalLowestFareRoundTrip": totalLowestFareRoundTrip,
            "totalLowestFareOneWayTrip": totalLowestFareOneWayTrip,
            "journeyType": journeyType,
            "cabinClass": cabinClass,
            "flightsAvailable": calendarAvailable
        }
    } else {
        const flightList = flights.toJS()[0];
        let lowestFare = 0;
        flightList.forEach(function (flight) {
            const offers = flight.offers;
            let offerKey = Object.keys(offers)[0];
            const offerValue = offers[offerKey].total[0];
            if (lowestFare === 0) {
                lowestFare = offerValue[0].amount;
            }
            if (lowestFare >= offerValue[0].amount) {
                lowestFare = offerValue[0].amount;
            }
        });
        tempFlightData = {
            "isMulticitySearch": true,
            "lowestAvailableFare": lowestFare,
            "lowestAvailableFareCurrency": currency,
            "journeyType": journeyType,
            "cabinClass": cabinClass,
            "flightResultsShownMultiCity": flightList.length
        }
    }

    if (journeyType === 'round-trip' && direction === 1) {
        flightData = tempFlightData;
    } else if (journeyType === 'one-way' || journeyType === 'multi-city') {
        flightData = tempFlightData;
    }


}


function initializeFlightData(currency, journeyType, cabinClass, flightsSize) {
    if (journeyType === 'multi-city') {
        tempFlightData = {
            "isMulticitySearch": true,
            "lowestAvailableFare": 0,
            "lowestAvailableFareCurrency": currency,
            "journeyType": journeyType,
            "cabinClass": cabinClass,
            "flightResultsShownMultiCity": 0,
            "flightsAvailable": flightsSize > 0 ? true : false
        }
    } else {
        tempFlightData = {
            "lowestAvailableFareCurrency": currency,
            "lowestAvailableFareOutbound": 0,
            "lowestAvailableFareReturn": 0,
            "flightResultsShownOutbound": 0,
            "flightResultsShownReturn": 0,
            "totalLowestFareRoundTrip": 0,
            "totalLowestFareOneWayTrip": 0,
            "journeyType": journeyType,
            "cabinClass": cabinClass,
            "flightsAvailable": flightsSize > 0 ? true : false
        }
    }

    flightData = tempFlightData;
}


function fnHandleK3DomManupulation() {
    var rows = document.querySelectorAll('.ancillary-cart-item.K3A:not([style*="display:none"]):not([style*="display: none"])');
    if (rows.length) {
        var origExtrasDOM = document.querySelector(".ancillaries-section");
        var origTaxDOM = document.querySelector(".taxes-section");

        if (origExtrasDOM && origTaxDOM) {
            var i = 0;
            var totalExtras = origExtrasDOM.querySelector('.number') ? parseFloat(origExtrasDOM.querySelector('.number').textContent.replace(',', '')) : 0;
            var totalTaxes = origTaxDOM.querySelector('.number') ? parseFloat(origTaxDOM.querySelector('.number').textContent.replace(',', '')) : 0;
            for (i; i < rows.length; i++) {
                var rowPrice = parseFloat(rows[i].querySelector('.number').textContent.replace(',', ''));
                if (!isNaN(rowPrice)) {
                    totalExtras -= rowPrice;
                    totalTaxes += rowPrice;
                    rows[i].querySelector("span").style.display = "none";
                    document.getElementsByClassName("tax-cart-items")[0].innerHTML += rows[i].innerHTML;
                    document.querySelector(".ancillaries-section").querySelector('.number').innerText = (totalExtras);
                    document.querySelector(".taxes-section").querySelector('.number').innerText = (totalTaxes);
                    rows[i].style.display = "none";
                }
            }
        }
    }
}

