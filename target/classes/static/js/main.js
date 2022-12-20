PrimeFaces.locales ['ru'] = {
    closeText: 'Закрыть',
    prevText: 'Назад',
    nextText: 'Вперёд',
    currentText: 'Home',
    monthNames: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'],
    monthNamesShort: ['Янв', 'Фев', 'Мар', 'Апр', 'Май', 'Июн', 'Июл', 'Авг', 'Сен', 'Окт', 'Ноя', 'Дек'],
    dayNames: ['Воскресенье', 'Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница', 'Суббота'],
    dayNamesShort: ['Воск', 'Пон', 'Вт', 'Ср', 'Четв', 'Пят', 'Суб'],
    dayNamesMin: ['Вс', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб'],
    weekHeader: 'Неделя',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Только время',
    timeText: 'Время',
    hourText: 'Час',
    minuteText: 'Минута',
    secondText: 'Секунда',
    currentText: 'Сегодня',
    ampm: false,
    month: 'Месяц',
    week: 'неделя',
    day: 'День',
    allDayText: 'Весь день'
};


$(document).ready(function () {

    $("#menu-button").on("click", function () {
        $("#mobile-menu").slideToggle().css('display', 'flex');
    });

    $('.ui-selectonebutton .ui-button').click(function () {
        $('#overlay-screen').fadeIn(100);
        setTimeout(applyFrontendScripts, 500);
    });

    applyFrontendScripts();

});

$(document).ready(function () {

    async function test() {

        document.getElementsByClassName('owl-carousel')[0].style.display = "none";
        document.getElementsByClassName('owl-carousel')[1].style.display = "none";
        document.getElementsByClassName('owl-carousel')[2].style.display = "none";
        console.log('start timer');
        await new Promise(resolve => setTimeout(resolve, 500));
        document.getElementsByClassName('owl-carousel')[0].style.display = "block";
        document.getElementsByClassName('owl-carousel')[1].style.display = "block";
        document.getElementsByClassName('owl-carousel')[2].style.display = "block";
        console.log('after 1 second');
    }

    test();



})

function applyFrontendScripts() {
    $('.ui-selectonebutton .ui-button').click(function () {
        $('#overlay-screen').fadeIn(100);
        setTimeout(applyFrontendScripts, 500);
    });

    $('.cert-frame').each(function () {
        if ($(this).width() < $(this).height()) {
            $(this).find('img:nth-child(2)').attr('src', '/images/cert-frame-v.png');
        }
    });

    if ($('#overlay-screen').css('display') == "block")
        $('#overlay-screen').fadeOut(500);

    // Show default image on image loading errors

    showDefaultImageOnError('.achievement-image img', '/images/achievements/star.svg');
    showDefaultImageOnError('.user-avatar img.avatar', '/images/ava1.svg');
    showDefaultImageOnError('.platform-card img.logo', '/images/platform-logo.png');
    showDefaultImageOnError('.course-card .platform-logo img', '/images/platform-logo.png');

    // Carousels on promo page

    var banner_el = $("#promo-top-banner");
    if(banner_el.owlCarousel){
        banner_el.owlCarousel({
            items: 1,
            nav: false,
            loop: true,
            autoplay: true,
            autoplayTimeout: 5000,
            autoplayHoverPause: false,
            smartSpeed: 450,
            dots: true,
            animateOut: 'fadeOut'
        });
    }
    var banner_el2 = $("#promo-top-banner2");
    if(banner_el2.owlCarousel){
        banner_el2.owlCarousel({
            items: 1,
            nav: false,
            loop: true,
            autoplay: true,
            autoplayTimeout: 5000,
            autoplayHoverPause: false,
            smartSpeed: 450,
            dots: true,
            animateOut: 'fadeOut'
        });
    }
    var banner_el3 = $("#promo-top-banner3");
    if(banner_el3.owlCarousel){
        banner_el3.owlCarousel({
            items: 1,
            nav: false,
            loop: true,
            autoplay: true,
            autoplayTimeout: 5000,
            autoplayHoverPause: false,
            smartSpeed: 450,
            dots: true,
            animateOut: 'fadeOut'
        });
    }

    const newsCount = $("#news-cards-carousel .owl-item").length;
    const newsOwlPrev = $("#news-cards-carousel .owl-prev");
    const newsOwlNext = $("#news-cards-carousel .owl-next");

    function onWindowResize() {
        const $window = $(window)
        if ($window.width() >= 1440 && newsCount <= 5) {
            newsOwlPrev.hide();
            newsOwlNext.hide();
        } else if ($window.width() >= 1200 && newsCount <= 4) {
            newsOwlPrev.hide();
            newsOwlNext.hide();
        } else if ($window.width() >= 992 && newsCount <= 3) {
            newsOwlPrev.hide();
            newsOwlNext.hide();
        } else if ($window.width() >= 768 && newsCount <= 2) {
            newsOwlPrev.hide();
            newsOwlNext.hide();
        } else if ($window.width() >= 0 && newsCount <= 1) {
            newsOwlPrev.hide();
            newsOwlNext.hide();
        } else {
            newsOwlPrev.show();
            newsOwlNext.show();
        }
    }

    // $(window).resize(function(){
    //     if($(window).width() < 768){
    //         $("#promo-activities-carousel").trigger('destroy.owl.carousel');
    //         $("#promo-activities-carousel").addClass('flexWhenCarouselStop');
    //     }
    // });
    $(function() {
        var owl = $('#promo-activities-carousel.owl-carousel'),
            owlOptions = {
                items: 1,
                loop: true,
                mouseDrag: true,
                touchDrag: true,
                dots: false,
                autoplay: true,
                autoplayTimeout: 3000,
                responsiveBaseElement: '#promo-activities-carousel'
            };

        if ( $(window).width() < 768 ) {
            var owlActive = owl.owlCarousel(owlOptions);
        } else {
            owl.addClass('off');
        }

        $(window).resize(function() {
            if ( $(window).width() < 768 ) {
                if ( $('#promo-activities-carousel.owl-carousel').hasClass('off') ) {
                    var owlActive = owl.owlCarousel(owlOptions);
                    owl.removeClass('off');
                }
            } else {
                if ( !$('#promo-activities-carousel.owl-carousel').hasClass('off') ) {
                    owl.addClass('off').trigger('destroy.owl.carousel');
                    owl.find('.owl-stage-outer').children(':eq(0)').unwrap();
                }
            }
        });
    });

    // $(function() {
    //     var owl_wrapper = $("#promo-activities-carousel");
    //     var owl = $('#promo-activities-carousel.owl-carousel'),
    //         owlOptions = {
    //             //items: 1,
    //             loop: true,
    //             mouseDrag: false,
    //             touchDrag: false,
    //             dots: false,
    //             autoplay: true,
    //             autoplayTimeout: 3000,
    //             responsiveBaseElement: '#promo-activities-carousel'
    //         };
    //
    //     if ( $(window).width() > 768 ) {
    //         var owlActive = owl.owlCarousel(owlOptions);
    //     } else {
    //         owl.addClass('off');
    //         owl_wrapper.addClass('flexWhenCarouselStop');
    //     }
    //
    //     $(window).resize(function() {
    //         if ( $(window).width() > 768 ) {
    //             if ( $('.owl-carousel').hasClass('off') ) {
    //                 var owlActive = owl.owlCarousel(owlOptions);
    //                 owl.removeClass('off');
    //                 owl_wrapper.removeClass('flexWhenCarouselStop');
    //             }
    //         } else {
    //             if ( !$('.owl-carousel').hasClass('off') ) {
    //                 owl.addClass('off').trigger('destroy.owl.carousel');
    //                 owl.find('.owl-stage-outer').children(':eq(0)').unwrap();
    //             }
    //         }
    //     });
    // });

    onWindowResize();
    $(window).on('resize', onWindowResize);
    if (newsCount === 0) news_el.parent().remove()

//    $(".promo-top-carousel").slick({
//        autoplay: true,
//        fade: true
//    });
//    
//    $(".cards-carousel").slick({
//        autoplay: true,
//        slidesToShow: 4,
//        slidesToScroll: 1
//    });
}

function openConversation() {
    $("#messages-form").addClass("conversation-opened");

//        $(".conversations-list").animate({
//            left: '-=100%'
//        }, "slow");
//        $(".conversation-details").animate({
//            left: '-=100%'
//        }, "slow");
}

function backToConversationsList() {
    $("#messages-form").removeClass("conversation-opened");
}

function pushRegistryHistory(data, title, value) {
    history.pushState(data, title, window.location.protocol+"//" + value);
}

// clip long text on course card and add "..."
// baseNode - parent element with limited height to clip to (block element)
// clippedNode - child element which content will be clipped (DOM node, not a JQuery object)
function clipText(baseNode, clippedNode) {
    /* temporaly off
    while (baseNode.scrollHeight > baseNode.clientHeight) {
        clippedNode.innerText = clippedNode.innerText.split(" ").slice(0,-1).join(' ') + "...";
    }
    */
}

function showDefaultImageOnError(selector, imageURL) {
    $(selector).each(function (key, item) {
        if ($(item).attr('src')) {
            $(item).on("error", function () {
                $(this).attr('src', imageURL);
                $(this).off("error");
            }).attr('src', $(item).attr('src')); // reload image and catch error
        } else {
            $(item).attr('src', imageURL); // if src is empty
        }
    });
}

function addCloseDialogByClickOutsideListener(widgetVar) {
    $("body").on("click",'.ui-dialog-mask', function () {
        if (widgetVar) {
            PF(widgetVar).hide();
        } else {
            $('.ui-dialog').hide();
            $('.ui-dialog-mask').hide();
        }
    })
}

$(document).ready(function() {
    let table = $('.fix-first-column .ui-datatable-tablewrapper');
    table.scroll(function(e) {
        $('.fix-first-column .ui-datatable-tablewrapper td:first-child').css("left", table.scrollLeft());
    });
});


function scrollToTop() {
    $('html, body').animate({scrollTop: 0}, 700)
}

$(document).ready(function () {
    $(window).bind('scroll', function () {
        if ($(window).scrollTop() > $(window).height()) {
            $('#toTop').fadeIn();
        } else {
            $('#toTop').fadeOut();
        }
        if ($(window).scrollTop() + $(window).height() < $(document).height() - $("#footer").height() - 50) {
            $('#toTop').css("position", "fixed");    //resetting it
        }
        if ($(window).scrollTop() + $(window).height() > $(document).height() - $("#footer").height() - 50) {
            $('#toTop').css("z-index", "500");
            $('#toTop').css("position", "absolute"); // make it related
        }
    });
});

var avatarN = 0;
function updateAvatarOnUpload(){
    $(".user-avatar img.avatar").each(function (key, item) {
        $(item).attr('src', $(item).attr('src').split("?")[0] + "?upd=" + avatarN);
    });
    avatarN++;
}
