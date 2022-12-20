/*!
 * Button visually impaired v1.0.3
 */
(function ($) {
    var voices = window.speechSynthesis.getVoices();

    function findVoice(lang) {
        for (var i = 0; i < voices.length; i++) {
            if (voices[i].lang.includes(lang)) {
                return voices[i];
            }
        }
        return null;
    }

    this.speak = function (s) {
        if (!window.speechSynthesis) {
            return;
        }
        var utterance = new SpeechSynthesisUtterance(s);
        utterance.lang = /[а-яА-ЯЁё0-9]/.test(s) ? "ru" : "en";
        utterance.voice = findVoice(utterance.lang);
        var isChromium = window.chrome;
        var winNav = window.navigator;
        var vendorName = winNav.vendor;
        var isOpera = typeof window.opr !== "undefined";
        var isIEedge = winNav.userAgent.indexOf("Edge") > -1;
        var isIOSChrome = winNav.userAgent.match("CriOS");

        if (isIOSChrome) {
            window.speechSynthesis.cancel();
            window.speechSynthesis.speak(utterance);
        } else if(
            isChromium !== null &&
            typeof isChromium !== "undefined" &&
            vendorName === "Google Inc." &&
            isOpera === false &&
            isIEedge === false
        ) {
            window.speechSynthesis.cancel();
            window.speechSynthesis.speak(utterance);
        } else {
            window.speechSynthesis.speak(utterance);
        }
    };

    function getSelectionText() {
        var text = "";
        var activeEl = document.activeElement;
        var activeElTagName = activeEl ? activeEl.tagName.toLowerCase() : null;
        if (
            (activeElTagName == "textarea") || (activeElTagName == "input" &&
            /^(?:text|search|password|tel|url)$/i.test(activeEl.type)) &&
            (typeof activeEl.selectionStart == "number")
        ) {
            text = activeEl.value.slice(activeEl.selectionStart, activeEl.selectionEnd);
        } else if (window.getSelection) {
            text = window.getSelection().toString();
        }
        return text;
    }

    function speakSelectedText(e) {
        let t = getSelectionText();
        speak(t);
    }

    var method = {
        Init: function (setting) {
            var selector = $(this).selector;
            var setting = $.extend({
                BviPanel: 1,
                BviPanelBg: "white",
                BviPanelFontFamily: "arial",
                BviEyeColor: 1,
                BviPanelFontSize: "18",
                BviPanelLetterSpacing: "normal",
                BviPanelLineHeight: "normal",
                BviPanelImg: 1,
                BviThemes: 1,
                BviPanelImgXY: 1,
                BviPanelReload: 0,
                BviPanelTemplateButtonDefault: null,
                BviPanelText: 'Версия для слабовидящих',
                BviPanelCloseText: "",
                BviCloseClassAndId: '',
                BviFixPanel: 0,
                BviPlay: 1
            }, setting);
            if (Cookies.get("bvi-panel") == "1") {
                $(setting.BviCloseClassAndId).hide();
                $('*').each(function () {
                    if (!$(this).attr('data-bvi-original'))
                        $(this).attr('data-bvi-original', $(this).attr('style'));

                });
                method.Panel(setting.BviCloseClassAndId, setting.BviFixPanel);
                method.Link(setting);
                $(selector).hide();
                $('.bvi-panel-open-menu').hide();
                $('.bvi-panel-open-menu').after($('<div class="bvi-panel-close">' +
                    '<span class="bvi-eye bvi-color"> ' +
                    '<svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="512px" height="512px" viewBox="0 0 512 512" xml:space="preserve" style="display: inline-block;"><g><path d="M256,128c-81.9,0-145.7,48.8-224,128c67.4,67.7,124,128,224,128c99.9,0,173.4-76.4,224-126.6 C428.2,198.6,354.8,128,256,128z M256,347.3c-49.4,0-89.6-41-89.6-91.3c0-50.4,40.2-91.3,89.6-91.3s89.6,41,89.6,91.3 C345.6,306.4,305.4,347.3,256,347.3z"></path><g><path d="M256,224c0-7.9,2.9-15.1,7.6-20.7c-2.5-0.4-5-0.6-7.6-0.6c-28.8,0-52.3,23.9-52.3,53.3c0,29.4,23.5,53.3,52.3,53.3 s52.3-23.9,52.3-53.3c0-2.3-0.2-4.6-0.4-6.9c-5.5,4.3-12.3,6.9-19.8,6.9C270.3,256,256,241.7,256,224z"> ' +
                    '</path> ' +
                    '</g> ' +
                    '</svg> ' +
                    '</span>' +
                    '</div>'));
                if (Cookies.get("bvi-panel-play") === '1') {
                    document.onmouseup = speakSelectedText;
                }
                method.BviPanelBgColor();
                method.BviPanelImg();
                method.BviPanelFontSize();
                method.BviPanelLetterSpacing();
                method.BviPanelLineHeight();
                method.BviPanelFontFamily();
                method.BviEyeColor();
                method.BviPlayIcon();
            } else {
                $(".bvi-panel-close-,.eye-disabled,#bvi-panel-close, .bvi-panel-close").remove();
                $(selector).show();
                $('object').show();
                $(setting.BviCloseClassAndId).show();
            }
            $(selector + ', .bvi-panel-open-menu').click(function () {
                if (Cookies.get("bvi-panel-play") === '0') {
                    document.onmouseup = null;
                }
                $(setting.BviCloseClassAndId).hide();
                $('*').each(function () {
                    if (!$(this).attr('data-bvi-original')) $(this).attr('data-bvi-original', $(this).attr('style'));
                    $('svg').hide();
                });
                Cookies.set("bvi-panel", setting.BviPanel, {
                    path: "/"
                });
                Cookies.set("bvi-panel-bg", setting.BviPanelBg, {
                    path: "/"
                });
                Cookies.set("bvi-panel-font-family", setting.BviPanelFontFamily, {
                    path: "/"
                });
                Cookies.set("bvi-panel-font-size", setting.BviPanelFontSize, {
                    path: "/"
                });
                Cookies.set("bvi-panel-letter-spacing", setting.BviPanelLetterSpacing, {
                    path: "/"
                });
                Cookies.set("bvi-panel-line-height", setting.BviPanelLineHeight, {
                    path: "/"
                });
                Cookies.set("bvi-panel-img", setting.BviPanelImg, {
                    path: "/"
                });
                Cookies.set("bvi-panel-img-X-Y", setting.BviPanelImgXY, {
                    path: "/"
                });
                Cookies.set("bvi-panel-reload", setting.BviPanelReload, {
                    path: "/"
                });
                Cookies.set("bvi-panel-play", setting.BviPlay, {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == '1') {
                    method.BviPlay();
                }
                method.Panel(setting.BviCloseClassAndId, setting.BviFixPanel);
                method.Link(setting);
                $(selector).hide();
                $('.bvi-panel-open-menu').hide();
                $('.bvi-panel-open-menu').after($('<div class="bvi-panel-close">' +
                    '<span class="bvi-eye bvi-color"> ' +
                    '<svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="512px" height="512px" viewBox="0 0 512 512" xml:space="preserve" style="display: inline-block;"><g><path d="M256,128c-81.9,0-145.7,48.8-224,128c67.4,67.7,124,128,224,128c99.9,0,173.4-76.4,224-126.6 C428.2,198.6,354.8,128,256,128z M256,347.3c-49.4,0-89.6-41-89.6-91.3c0-50.4,40.2-91.3,89.6-91.3s89.6,41,89.6,91.3 C345.6,306.4,305.4,347.3,256,347.3z"></path><g><path d="M256,224c0-7.9,2.9-15.1,7.6-20.7c-2.5-0.4-5-0.6-7.6-0.6c-28.8,0-52.3,23.9-52.3,53.3c0,29.4,23.5,53.3,52.3,53.3 s52.3-23.9,52.3-53.3c0-2.3-0.2-4.6-0.4-6.9c-5.5,4.3-12.3,6.9-19.8,6.9C270.3,256,256,241.7,256,224z"> ' +
                    '</path> ' +
                    '</g> ' +
                    '</svg> ' +
                    '</span>' +
                    '</div>'));
                method.BviPanelBgColor();
                method.BviPanelImg();
                method.BviPanelFontSize();
                method.BviPanelLetterSpacing();
                method.BviPanelLineHeight();
                method.BviPanelFontFamily();
                method.BviEyeColor();
                method.BviPlayIcon();
                if (Cookies.get("bvi-panel-play") === '1') {
                    document.onmouseup = speakSelectedText;
                }
                $('#bvi-panel-close, .bvi-panel-close').click(function () {
                    Cookies.set("bvi-panel", "0", {
                        path: "/"
                    });
                    $(".bvi-panel-close-,.eye-disabled,#bvi-panel-close, .bvi-panel-close").remove();
                    $(".bvi-panel-menu").remove();
                    $(".bvi-panel-img-not").remove();
                    $(".bvi-grayscale").remove();
                    $('*').each(function () {
                        $('svg').show();
                    });
                    $('object').show();
                    method.Panel(setting.BviCloseClassAndId, setting.BviFixPanel);
                    method.BviEyeColor();
                    return false;
                });
                return false;
            });
            $('#bvi-panel-close, .bvi-panel-close').click(function () {
                Cookies.set("bvi-panel", "0", {
                    path: "/"
                });
                $(".bvi-panel-close-,.eye-disabled,#bvi-panel-close, .bvi-panel-close").remove();
                $(".bvi-panel-menu").remove();
                $(".bvi-panel-img-not").remove();
                $(".bvi-grayscale").remove();
                $('*').each(function () {
                    $('svg').show();
                });
                method.Panel(setting.BviCloseClassAndId, setting.BviFixPanel);
                method.BviEyeColor();
                return false;
            });
        },
        BviMobile: function () {
            return $.browser.device = (/android|webos|iphone|ipad|ipod|blackberry|iemobile|opera mini/i.test(navigator.userAgent.toLowerCase()));
        },
        BviEyeColor: function () {
            if (Cookies.get('bvi-panel-bg') == 'white') {
                $('.bvi-eye').addClass('eye-disabled-black');
            } else {
                $('.bvi-eye').removeClass('eye-disabled-black');
                $('.bvi-color').removeClass('eye-disabled-black');
            }
            if (Cookies.get('bvi-panel-bg') == 'black') {
                $('.bvi-eye').addClass('eye-disabled-white');
            } else {
                $('.bvi-eye').removeClass('eye-disabled-white');
                $('.bvi-color').removeClass('eye-disabled-white');
            }
            if (Cookies.get('bvi-panel-bg') == 'blue') {
                $('.bvi-eye').addClass('eye-disabled-blue');
            } else {
                $('.bvi-eye').removeClass('eye-disabled-blue');
                $('.bvi-color').removeClass('eye-disabled-blue');
            }
            if (Cookies.get('bvi-panel-bg') == 'brown') {
                $('.bvi-eye').addClass('eye-disabled-brown');
            } else {
                $('.bvi-eye').removeClass('eye-disabled-brown');
                $('.bvi-color').removeClass('eye-disabled-brown');
            }
            if (Cookies.get('bvi-panel-bg') == 'green') {
                $('.bvi-eye').addClass('eye-disabled-green');
            } else {
                $('.bvi-eye').removeClass('eye-disabled-green');
                $('.bvi-color').removeClass('eye-disabled-green');
            }
        },
        BviPanelFontFamily: function () {
            if (Cookies.get("bvi-panel-font-family") == "arial") {
                $("#bvi-panel-font-family-arial").addClass("active");
                method.ReturnSet({
                    fontfamily: "Arial, sans-serif"
                })
            } else {
                $("#bvi-panel-font-family-arial").removeClass("active")
            }
            if (Cookies.get("bvi-panel-font-family") == "times") {
                $("#bvi-panel-font-family-times-new-roman").addClass("active");
                method.ReturnSet({
                    fontfamily: '"Times New roman", serif'
                })
            } else {
                $("#bvi-panel-font-family-times-new-roman").removeClass("active")
            }
        },
        BviPanelLetterSpacing: function () {
            if (Cookies.get("bvi-panel-letter-spacing") === "normal") {
                $("#bvi-panel-letter-spacing-normal").addClass("active");
                method.ReturnSet({
                    letterspacing: "0px"
                })
            } else {
                $("#bvi-panel-letter-spacing-normal").removeClass("active")
            }
            if (Cookies.get("bvi-panel-letter-spacing") === "average") {
                $("#bvi-panel-letter-spacing-average").addClass("active");
                method.ReturnSet({
                    letterspacing: "2px"
                })
            } else {
                $("#bvi-panel-letter-spacing-average").removeClass("active")
            }
            if (Cookies.get("bvi-panel-letter-spacing") === "big") {
                $("#bvi-panel-letter-spacing-big").addClass("active");
                method.ReturnSet({
                    letterspacing: "5px"
                })
            } else {
                $("#bvi-panel-letter-spacing-big").removeClass("active")
            }
        },
        BviPanelLineHeight: function () {
            if (Cookies.get("bvi-panel-line-height") === "normal") {
                $("#bvi-panel-line-height-normal").addClass("active");
                method.ReturnSet({
                    lineheight: "normal"
                })
            } else {
                $("#bvi-panel-line-height-normal").removeClass("active")
            }
            if (Cookies.get("bvi-panel-line-height") === "average") {
                $("#bvi-panel-line-height-average").addClass("active");
                method.ReturnSet({
                    lineheight: "2"
                })
            } else {
                $("#bvi-panel-line-height-average").removeClass("active")
            }
            if (Cookies.get("bvi-panel-line-height") === "big") {
                $("#bvi-panel-line-height-big").addClass("active");
                method.ReturnSet({
                    lineheight: "3"
                })
            } else {
                $("#bvi-panel-line-height-big").removeClass("active")
            }
        },
        BviPanelFontSize: function () {
            if (Cookies.get("bvi-panel-font-size") == "14") {
                $("#bvi-panel-font-size-14").addClass("active");
                method.ReturnSet({
                    fontsize: "14px"
                });
            } else {
                $("#bvi-panel-font-size-14").removeClass("active")
            }
            if (Cookies.get("bvi-panel-font-size") == "16") {
                $("#bvi-panel-font-size-16").addClass("active");
                method.ReturnSet({
                    fontsize: "16px"
                })
            } else {
                $("#bvi-panel-font-size-16").removeClass("active")
            }
            if (Cookies.get("bvi-panel-font-size") == "18") {
                $("#bvi-panel-font-size-18").addClass("active");
                method.ReturnSet({
                    fontsize: "18px"
                })
            } else {
                $("#bvi-panel-font-size-18").removeClass("active")
            }
            if (Cookies.get("bvi-panel-font-size") == "20") {
                $("#bvi-panel-font-size-20").addClass("active");
                method.ReturnSet({
                    fontsize: "20px"
                })
            } else {
                $("#bvi-panel-font-size-20").removeClass("active")
            }
            if (Cookies.get("bvi-panel-font-size") == "23") {
                $("#bvi-panel-font-size-23").addClass("active");
                method.ReturnSet({
                    fontsize: "23px"
                })
            } else {
                $("#bvi-panel-font-size-23").removeClass("active")
            }
        },
        BviPanelBgColor: function () {
            if (Cookies.get("bvi-panel-bg") == "white") {
                $("#bvi-panel-bg-white").addClass("active");
                method.ReturnSet({
                    backgroundcolor: "#ffffff",
                    color: "#000000"
                });
            } else {
                $("#bvi-panel-bg-white").removeClass("active");
            }
            if (Cookies.get("bvi-panel-bg") == "black") {
                $("#bvi-panel-bg-black").addClass("active");
                method.ReturnSet({
                    backgroundcolor: "#000000",
                    color: "#ffffff"
                });
            } else {
                $("#bvi-panel-bg-black").removeClass("active");
            }
            if (Cookies.get("bvi-panel-bg") == "blue") {
                $("#bvi-panel-bg-blue").addClass("active");
                method.ReturnSet({
                    backgroundcolor: "#9DD1FF",
                    color: "#063462"
                });
            } else {
                $("#bvi-panel-bg-blue").removeClass("active");
            }
            if (Cookies.get("bvi-panel-bg") == "brown") {
                $("#bvi-panel-bg-brown").addClass("active");
                method.ReturnSet({
                    backgroundcolor: "#f7f3d6",
                    color: "#4d4b43"
                });
            } else {
                $("#bvi-panel-bg-brown").removeClass("active");
            }
            if (Cookies.get("bvi-panel-bg") == "green") {
                $("#bvi-panel-bg-green").addClass("active");
                method.ReturnSet({
                    backgroundcolor: "#3B2716",
                    color: "#A9E44D"
                });
            } else {
                $("#bvi-panel-bg-green").removeClass("active");
            }
        },
        BviPanelImg: function () {
            $(".bvi-panel-img-not").remove();
            if (Cookies.get("bvi-panel-img") == 1) {
                $("img, .logo, .image").each(function () {
                    $(".bvi-grayscale").removeClass("bvi-grayscale");
                    $(".bvi-panel-img-not").remove();
                    $(this).show();
                });
                $("#bvi-panel-img-grayScale").removeClass('active');
                $("#bvi-panel-img-off").removeClass('active');
                $("#bvi-panel-img-on").addClass('active');
                $(".bvi-grayscale").removeClass("bvi-grayscale");
                $(".bvi-panel-img-not").remove();
            } else {
                $(".bvi-grayscale").removeClass("bvi-grayscale");
                if (Cookies.get("bvi-panel-img") == 'grayScale') {
                    $("img, .logo, .image").each(function () {
                        var alt = this.alt || "нет описания к изображению";
                        var src = this.src;
                        var imgClass = $(this).attr("class") || 'bvi-class';
                        var imgId = $(this).attr("id") || 'bvi-panel-img-not';
                        $(this).addClass("bvi-grayscale");
                        $(".bvi-grayscale").css({
                            filter: 'progid:DXImageTransform.Microsoft.BasicImage(grayScale=1)',
                            MsFilter: 'progid:DXImageTransform.Microsoft.BasicImage(grayscale=1)'
                        });
                    });
                    $("#bvi-panel-img-grayScale").addClass('active');
                    $("#bvi-panel-img-off").removeClass('active');
                    $("#bvi-panel-img-on").removeClass('active');
                    $('.coin-slider').show();
                } else {
                    $("img, .logo, .image").each(function () {
                        var alt = this.alt || "нет описания к изображению";
                        var imgClass = $(this).attr("class") || 'bvi-class';
                        var imgId = $(this).attr("id") || 'bvi-panel-img-not';
                        $(this).hide();
                        if (Cookies.get("bvi-panel-img-X-Y") == 1) {
                            $(this).after($('<div class="bvi-panel-img-not ' + imgClass + '" id="' + imgId + '" style="width: ' + $(this).width() + "px; height: " + $(this).height() + 'px">').html("Изображение : " + alt))
                        } else {
                            $(this).after($('<div class="bvi-panel-img-not">').text("Изображение : " + alt))
                        }
                    });
                    $("#bvi-panel-img-grayScale").removeClass('active');
                    $("#bvi-panel-img-off").addClass('active');
                    $("#bvi-panel-img-on").removeClass('active');
                }
            }
        },
        BviPlay: function () {
            if (Cookies.get("bvi-panel-play") == '1') {
                speak('Версия сайта для слабовидящих');
            } else {
                speak('Обычная версия сайта');
            }
        },
        BviPlayIcon: function () {
            if (Cookies.get("bvi-panel-play") == '1') {
                $('#bvi-panel-play-on').addClass('bvi-panel-menu-hidden');
                $('#bvi-panel-play-off').removeClass('bvi-panel-menu-hidden');
            } else {
                $('#bvi-panel-play-off').addClass('bvi-panel-menu-hidden');
                $('#bvi-panel-play-on').removeClass('bvi-panel-menu-hidden');
            }
        },
        Link: function (setting) {
            $("#bvi-panel-play-on").click(function () {
                Cookies.set("bvi-panel-play", "1", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == '1') {
                    speak('Синтез речи включен');
                    document.onmouseup = speakSelectedText;
                }
                method.BviPlayIcon();
                return false;
            });
            $("#bvi-panel-play-off").click(function () {
                Cookies.set("bvi-panel-play", "0", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") === '0') {
                    speak('Синтез речи выключен');
                    document.onmouseup = null;
                }
                method.BviPlayIcon();
                return false;
            });
            $("#bvi-panel-img-on").click(function () {
                Cookies.set("bvi-panel-img", "1", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == '1') {
                    speak('Изображения включены');
                }
                method.BviPanelImg();
                return false;
            });
            $("#bvi-panel-img-off").click(function () {
                Cookies.set("bvi-panel-img", "0", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == '1') {
                    speak("Изображения выключены");
                }

                method.BviPanelImg();
                return false;
            });
            $("#bvi-panel-img-grayScale").click(function () {
                Cookies.set("bvi-panel-img", "1", {
                    path: "/"
                });
                method.BviPanelImg();
                Cookies.set("bvi-panel-img", "grayScale", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Изображения черно-белые');
                }
                method.BviPanelImg();
                return false;
            });
            $("#bvi-panel-bg-white").click(function () {
                Cookies.set("bvi-panel-bg", "white", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Цвет сайта: Черным по белому');
                }
                method.BviPanelBgColor();
                method.BviEyeColor();
                return false;
            });
            $("#bvi-panel-bg-black").click(function () {
                Cookies.set("bvi-panel-bg", "black", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Цвет сайта: Белым по черному');
                }
                method.BviPanelBgColor();
                method.BviEyeColor();
                return false;
            });
            $("#bvi-panel-bg-blue").click(function () {
                Cookies.set("bvi-panel-bg", "blue", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Цвет сайта: Темно-синим по голубому');
                }
                method.BviPanelBgColor();
                method.BviEyeColor();
                return false;
            });
            $("#bvi-panel-bg-brown").click(function () {
                Cookies.set("bvi-panel-bg", "brown", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Цвет сайта: Коричневым по бежевому');
                }
                method.BviPanelBgColor();
                method.BviEyeColor();
                return false;
            });
            $("#bvi-panel-bg-green").click(function () {
                Cookies.set("bvi-panel-bg", "green", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Цвет сайта: Зеленым по темно-коричневому');
                }
                method.BviPanelBgColor();
                method.BviEyeColor();
                return false;
            });
            $("#bvi-panel-font-size-14").click(function () {
                Cookies.set("bvi-panel-font-size", "14", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Размер шрифта 14 пикселей');
                }
                method.BviPanelFontSize();
                return false;
            });
            $("#bvi-panel-font-size-16").click(function () {
                Cookies.set("bvi-panel-font-size", "16", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Размер шрифта 16 пикселей');
                }
                method.BviPanelFontSize();
                return false;
            });
            $("#bvi-panel-font-size-18").click(function () {
                Cookies.set("bvi-panel-font-size", "18", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Размер шрифта 18 пикселей');
                }
                method.BviPanelFontSize();
                return false;
            });
            $("#bvi-panel-font-size-20").click(function () {
                Cookies.set("bvi-panel-font-size", "20", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Размер шрифта 20 пикселей');
                }
                method.BviPanelFontSize();
                return false;
            });
            $("#bvi-panel-font-size-23").click(function () {
                Cookies.set("bvi-panel-font-size", "23", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Размер шрифта 23 пикселя');
                }
                method.BviPanelFontSize();
                return false;
            });
            $("#bvi-panel-settings,.bvi-panel-settings-close").click(function () {
                $(".bvi-panel-settings").slideToggle("slow");
                $('#bvi-panel-settings').toggleClass('active');
                return false;
            });
            $("#bvi-panel-font-family-arial").click(function () {
                Cookies.set("bvi-panel-font-family", "arial", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Гарнитура без засечек');
                }
                method.BviPanelFontFamily();
                return false;
            });
            $("#bvi-panel-font-family-times-new-roman").click(function () {
                Cookies.set("bvi-panel-font-family", "times", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Гарнитура с засечками');
                }
                method.BviPanelFontFamily();
                return false;
            });
            $("#bvi-panel-letter-spacing-normal").click(function () {
                Cookies.set("bvi-panel-letter-spacing", "normal", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Кернинг стандартный');
                }
                method.BviPanelLetterSpacing();
                return false;
            });
            $("#bvi-panel-letter-spacing-average").click(function () {
                Cookies.set("bvi-panel-letter-spacing", "average", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Кернинг средний');
                }
                method.BviPanelLetterSpacing();
                return false;
            });
            $("#bvi-panel-letter-spacing-big").click(function () {
                Cookies.set("bvi-panel-letter-spacing", "big", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Кернинг большой');
                }
                method.BviPanelLetterSpacing();
                return false;
            });
            $("#bvi-panel-line-height-normal").click(function () {
                Cookies.set("bvi-panel-line-height", "normal", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Интервал стандартный');
                }
                method.BviPanelLineHeight();
                return false;
            });
            $("#bvi-panel-line-height-average").click(function () {
                Cookies.set("bvi-panel-line-height", "average", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Интервал средний');
                }
                method.BviPanelLineHeight();
                return false;
            });
            $("#bvi-panel-line-height-big").click(function () {
                Cookies.set("bvi-panel-line-height", "big", {
                    path: "/"
                });
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Интервал большой');
                }
                method.BviPanelLineHeight();
                return false;
            });
            $('#bvi-panel-show').click(function () {
                $('.bvi-panel-row').css({
                    "display": "block"
                });
                $('.bvi-panel-hide').css({
                    "display": "none"
                });
                return false;
            });
            $('#bvi-panel-hide').click(function () {
                $('.bvi-panel-row').css({
                    "display": "none"
                });
                $('.bvi-panel-show').css({
                    "display": "block",
                    "position": "fixed !important",
                    "top": "2.5rem !important",
                    "right": "1.5rem !important",
                    "z-index": "999999 !important"
                });
                return false;
            });
            $('#bvi-rerender-website').click(function () {
                method.BviPanelImg();
                method.BviPanelBgColor();
                method.BviPanelFontSize();
                method.BviPanelLetterSpacing();
                method.BviPanelLineHeight();
                method.BviPanelFontFamily();
                return false;
            });
            $(".bvi-panel-settings-default").click(function () {
                Cookies.set("bvi-panel", setting.BviPanel, {
                    path: "/"
                });
                Cookies.set("bvi-panel-bg", setting.BviPanelBg, {
                    path: "/"
                });
                Cookies.set("bvi-panel-font-family", setting.BviPanelFontFamily, {
                    path: "/"
                });
                Cookies.set("bvi-panel-font-size", setting.BviPanelFontSize, {
                    path: "/"
                });
                Cookies.set("bvi-panel-letter-spacing", setting.BviPanelLetterSpacing, {
                    path: "/"
                });
                Cookies.set("bvi-panel-line-height", setting.BviPanelLineHeight, {
                    path: "/"
                });
                Cookies.set("bvi-panel-img", setting.BviPanelImg, {
                    path: "/"
                });
                Cookies.set("bvi-panel-img-X-Y", setting.BviPanelImgXY, {
                    path: "/"
                });
                Cookies.set("bvi-panel-reload", setting.BviPanelReload, {
                    path: "/"
                });
                Cookies.set("bvi-panel-play", setting.BviPlay, {
                    path: "/"
                });
                method.BviPanelImg();
                method.BviPanelBgColor();
                method.BviPanelFontSize();
                method.BviPanelLetterSpacing();
                method.BviPanelLineHeight();
                method.BviPanelFontFamily();
                document.onmouseup = speakSelectedText;
                if (Cookies.get("bvi-panel-play") == 1) {
                    speak('Настройки по умолчанию сброшены');
                }
                return false;
            })
        },
        ReturnSet: function (setting) {
            $("*").not(".bvi-panel-menu,.bvi-panel-menu *, .fa, .glyphicon, .dashicons, .ignore-bvi *").each(function () {
                $(this).css({
                    "font-family": setting.fontfamily,
                    "background-color": setting.backgroundcolor,
                    "color": setting.color,
                    "font-size": setting.fontsize,
                    "box-shadow": "none",
                    "text-shadow": "none",
                    "letter-spacing": setting.letterspacing,
                    "border-color": setting.color,
                    "line-height": setting.lineheight
                });
            })
        },
        Panel: function (BviCloseClassAndId, BviFixPanel) {
            if (Cookies.get("bvi-panel") == "1") {
                $('<div class="bvi-panel-menu"></div>').prependTo("body");
                $(".bvi-panel-menu").addClass("bvi-panel-animated bvi-panel-fadeInDown");
                var scroll = (window.pageYOffset !== undefined) ? window.pageYOffset : (document.documentElement || document.body.parentNode || document.body).scrollTop;
                if (BviFixPanel == '1') {
                    if (scroll > 99) {
                        $(".bvi-panel-menu").addClass("bvi-panel-fixed");
                    }
                    $(window).scroll(function () {
                        if ($(this).scrollTop() >= 99) {
                            $(".bvi-panel-menu").addClass("bvi-panel-fixed");
                            $(".bvi-panel-menu").css({
                                "left": "0",
                                "margin-bottom": "20px",
                                "margin-left": "auto",
                                "margin-right": "auto",
                                "position": "fixed",
                                "right": 0,
                                "z-index": 99999
                            });
                        } else {
                            $(".bvi-panel-menu").removeClass("bvi-panel-fixed");
                            $(".bvi-panel-menu").removeAttr("style");
                        }
                    });
                }

                $('<div class="bvi-panel-container">' +
                    '<div class="bvi-panel-row">' +
                    '<div class="bvi-padding">' +
                    '<div class="bvi-panel-menu-bg">' +
                    '<div class="bvi-panel-row">' +
                    '<div class="bvi-panel-col-xs-12 bvi-panel-col-sm-6 bvi-panel-col-md-3 bvi-panel-col-lg-3">' +
                    '<div class="bvi-panel-menu-block">' +
                    '<div class="bvi-panel-title">Размер шрифта</div>' +
                    '<div class="bvi-panel-btn-group" role="group" aria-label="Размер шрифта">' +
                    '<a href="#" id="bvi-panel-font-size-14" class="bvi-panel-btn bvi-panel-btn-default bvi-panel-font-size-14" title="Размер шрифта 14 пикселей">А</a>' +
                    '<a href="#" id="bvi-panel-font-size-16" class="bvi-panel-btn bvi-panel-btn-default bvi-panel-font-size-16" title="Размер шрифта 16 пикселей">А</a>' +
                    '<a href="#" id="bvi-panel-font-size-18" class="bvi-panel-btn bvi-panel-btn-default bvi-panel-font-size-18" title="Размер шрифта 18 пикселей">А</a>' +
                    '<a href="#" id="bvi-panel-font-size-20" class="bvi-panel-btn bvi-panel-btn-default bvi-panel-font-size-20" title="Размер шрифта 20 пикселей">А</a>' +
                    '<a href="#" id="bvi-panel-font-size-23" class="bvi-panel-btn bvi-panel-btn-default bvi-panel-font-size-23" title="Размер шрифта 23 пикселя">А</a>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="bvi-panel-col-xs-12 bvi-panel-col-sm-6 bvi-panel-col-md-3 bvi-panel-col-lg-3">' +
                    '<div class="bvi-panel-menu-block">' +
                    '<div class="bvi-panel-title">Цвета сайта</div>' +
                    '<div class="bvi-panel-btn-group bvi-panel-bg-color" role="group" aria-label="Цвета сайта">' +
                    '<a href="#" id="bvi-panel-bg-white" class="bvi-panel-btn bvi-panel-btn-black-white" title="Цвет сайта: Черным по белому">Ц</a>' +
                    '<a href="#" id="bvi-panel-bg-black" class="bvi-panel-btn bvi-panel-btn-white-black" title="Цвет сайта: Белым по черному">Ц</a>' +
                    '<a href="#" id="bvi-panel-bg-blue" class="bvi-panel-btn bvi-panel-btn bvi-panel-btn-blue" title="Цвет сайта: Темно-синим по голубому">Ц</a>' +
                    '<a href="#" id="bvi-panel-bg-brown" class="bvi-panel-btn bvi-panel-btn-brown" title="Цвет сайта: Коричневым по бежевому">Ц</a>' +
                    '<a href="#" id="bvi-panel-bg-green" class="bvi-panel-btn bvi-panel-btn-green" title="Цвет сайта: Зеленым по темно-коричневому">Ц</a>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="bvi-panel-col-xs-12 bvi-panel-col-sm-6 bvi-panel-col-md-3 bvi-panel-col-lg-3">' +
                    '<div class="bvi-panel-menu-block">' +
                    '<div class="bvi-panel-title">Изображения</div>' +
                    '<div class="bvi-panel-btn-group" role="group" aria-label="Изображения">' +
                    '<a href="#" id="bvi-panel-img-grayScale" class="bvi-panel-btn bvi-panel-btn-default"><i class="bvi-panel-glyphicon bvi-panel-glyphicon-picture" style="color: grey;"></i> Ч/Б</a>' +
                    '<a href="#" id="bvi-panel-img-on" class="bvi-panel-btn bvi-panel-btn-default"><i class="bvi-panel-glyphicon bvi-panel-glyphicon-ok-circle" style="color: green;"></i> Вкл.</a>' +
                    '<a href="#" id="bvi-panel-img-off" class="bvi-panel-btn bvi-panel-btn-default"><i class="bvi-panel-glyphicon bvi-panel-glyphicon-remove-circle" style="color: red;"></i> Выкл.</a>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="bvi-panel-col-xs-12 bvi-panel-col-sm-6 bvi-panel-col-md-3 bvi-panel-col-lg-3">' +
                    '<div class="bvi-panel-menu-block">' +
                    '<div class="bvi-panel-title">Дополнительно</div>' +
                    '<div class="bvi-panel-btn-toolbar" role="toolbar" aria-label="...">' +
                    '<a href="#" id="bvi-panel-play-off" class="bvi-panel-btn bvi-panel-btn-default" title="Выключить синтез речи"><span class="bvi-panel-glyphicon bvi-panel-glyphicon-volume-off"></span></a>' +
                    '<a href="#" id="bvi-panel-play-on" class="bvi-panel-btn bvi-panel-btn-default" title="Включить синтез речи"><span class="bvi-panel-glyphicon bvi-panel-glyphicon-volume-up"></span></a>' +
                    '</div>' +
                    '<div class="bvi-panel-btn-group" role="group" aria-label="Дополнительно">' +
                    '<a href="#" id="bvi-panel-settings" class="bvi-panel-btn bvi-panel-btn-default" title="Настройки"><span class="bvi-panel-glyphicon bvi-panel-glyphicon-cog"></span> Настройки</a>' +
                    '<a href="#" id="bvi-panel-close" class="bvi-panel-btn bvi-panel-btn-default" title="Обычная версия сайта"><i class="bvi-panel-glyphicon bvi-panel-glyphicon-eye-close"></i></a>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="bvi-panel-row">' +
                    '<div class="bvi-panel-col-xs-12 bvi-panel-col-sm-12 bvi-panel-col-md-12 bvi-panel-col-lg-12">' +
                    '<div class="bvi-panel-row">' +
                    '<div class="bvi-panel-settings">' +
                    '<hr>' +
                    '<div class="bvi-panel-col-xs-12 bvi-panel-col-sm-6 bvi-panel-col-md-4 bvi-panel-col-lg-4">' +
                    '<div class="bvi-panel-title">Кернинг </div>' +
                    '<div class="bvi-panel-btn-group" role="group" aria-label="Кернинг">' +
                    '<a href="#" id="bvi-panel-letter-spacing-normal" class="bvi-panel-btn bvi-panel-btn-default">Стандартный</a>' +
                    '<a href="#" id="bvi-panel-letter-spacing-average" class="bvi-panel-btn bvi-panel-btn-default">Средний</a>' +
                    '<a href="#" id="bvi-panel-letter-spacing-big" class="bvi-panel-btn bvi-panel-btn-default">Большой</a>' +
                    '</div>' +
                    '</div>' +
                    '<div class="bvi-panel-col-xs-12 bvi-panel-col-sm-6 bvi-panel-col-md-4 bvi-panel-col-lg-4">' +
                    '<div class="bvi-panel-title">Интервал </div>' +
                    '<div class="bvi-panel-btn-group" role="group" aria-label="Интервал">' +
                    '<a href="#" id="bvi-panel-line-height-normal" class="bvi-panel-btn bvi-panel-btn-default">Стандартный</a>' +
                    '<a href="#" id="bvi-panel-line-height-average" class="bvi-panel-btn bvi-panel-btn-default">Средний</a>' +
                    '<a href="#" id="bvi-panel-line-height-big" class="bvi-panel-btn bvi-panel-btn-default">Большой</a>' +
                    '</div>' +
                    '</div>' +
                    '<div class="bvi-panel-col-xs-12 bvi-panel-col-sm-12 bvi-panel-col-md-4 bvi-panel-col-lg-4">' +
                    '<div class="bvi-panel-title">Гарнитура </div>' +
                    '<div class="bvi-panel-btn-group" role="group" aria-label="Гарнитура">' +
                    '<a href="#" id="bvi-panel-font-family-arial" class="bvi-panel-btn bvi-panel-btn-default">Без засечек</a>' +
                    '<a href="#" id="bvi-panel-font-family-times-new-roman" class="bvi-panel-btn bvi-panel-btn-default">С засечками</a>' +
                    '</div>' +
                    '</div>' +
                    '<div class="bvi-panel-col-xs-12 bvi-panel-col-sm-12 bvi-panel-col-md-4 bvi-panel-col-lg-4" style="margin-top: 15px;">' +
                    '<a href="#" id="bvi-panel-settings-default" class="bvi-panel-btn bvi-panel-btn-default bvi-panel-settings-default bvi-panel-btn-block"><i class="bvi-panel-glyphicon bvi-panel-glyphicon-refresh"></i> Вернуть стандартные настройки</a>' +
                    '</div>' +
                    '<div class="bvi-panel-col-xs-12 bvi-panel-col-sm-12 bvi-panel-col-md-4 bvi-panel-col-lg-4" style="margin-top: 15px;">' +
                    '<a href="#" id="bvi-panel-close" class="bvi-panel-btn bvi-panel-btn-default bvi-panel-settings-close bvi-panel-btn-block" title="Обычная версия сайта"><i class="bvi-panel-glyphicon bvi-panel-glyphicon-eye-close"></i> Обычная версия сайта</a>' +
                    '</div>' +
                    '<div class="bvi-panel-col-xs-12 bvi-panel-col-sm-12 bvi-panel-col-md-4 bvi-panel-col-lg-4" style="margin-top: 15px;">' +
                    '<a href="#" id="bvi-panel-settings" class="bvi-panel-btn bvi-panel-btn-default bvi-panel-settings-close bvi-panel-btn-block" title="Закрыть"><i class="bvi-panel-glyphicon bvi-panel-glyphicon-remove"></i> Закрыть</a>' +
                    '</div>' +
                    '<div class="bvi-panel-col-xs-12 bvi-panel-col-sm-12 bvi-panel-col-md-12 bvi-panel-col-lg-12" style="margin-top: 15px;">' +
                    '</div>' +
                    '<div id="bvi-rerender-website" style="display: none;">' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>'
                ).appendTo(".bvi-panel-menu");
            } else {
                $("#bvi-panel-close, .bvi-panel-close").remove();
                $('*').each(function () {
                    $(this).removeAttr("style", 'font-family');
                    $(this).removeAttr("style", 'background-color');
                    $(this).removeAttr("style", 'color');
                    $(this).removeAttr("style", 'font-size');
                    $(this).removeAttr("style", 'box-shadow');
                    $(this).removeAttr("style", 'text-shadow');
                    $(this).removeAttr("style", 'letter-spacing');
                    $(this).removeAttr("style", 'border-color');
                    $(this).attr("style", $(this).attr('data-bvi-original'));
                    $(this).removeAttr('data-bvi-original');
                    $('svg').show();
                });
                $(BviCloseClassAndId).show();
                if (Cookies.get("bvi-panel-reload") == "1") {
                    document.location.reload(true)
                }
                Cookies.remove("bvi-panel", {
                    path: "/"
                });
                Cookies.remove("bvi-panel-bg", {
                    path: "/"
                });
                Cookies.remove("bvi-panel-font-family", {
                    path: "/"
                });
                Cookies.remove("bvi-panel-font-size", {
                    path: "/"
                });
                Cookies.remove("bvi-panel-letter-spacing", {
                    path: "/"
                });
                Cookies.remove("bvi-panel-line-height", {
                    path: "/"
                });
                Cookies.remove("bvi-panel-img", {
                    path: "/"
                });
                Cookies.remove("bvi-panel-img-X-Y", {
                    path: "/"
                });
                Cookies.remove("bvi-panel-reload", {
                    path: "/"
                });
                Cookies.remove("bvi-panel-play", {
                    path: "/"
                });
                method.BviPlay();
                document.onmouseup = null;
            }
        }
    };
    $.fn.bvi = function (setting) {
        if (method[setting]) {
            return method[setting].apply(this, Array.prototype.slice.call(arguments, 1))
        } else {
            if (typeof setting === "object" || !setting) {
                return method.init.apply(this, arguments)
            } else {
                b.error("Метод с именем " + c + " не существует для jQuery.bvi")
            }
        }
    }
})(jQuery);
