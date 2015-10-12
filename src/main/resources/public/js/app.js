
var Circles = {
        __wrapperID: 'circle-wrapper',
        __startAngle: 141,
        __restaurantCache: {},
        __CATEGORIES: ['coffee', 'bakery', 'breakfast', 'foodcart', 'lunch', 'dinner', 'dessert', 'happyhour', 'drinks'],

        __classes: {
          circle: 'category',
          linedUp: 'circle-lined-up',
          oneCircle: 'circle-one-circle'
        },

        get wrapper() {
          return document.getElementById(Circles.__wrapperID);
        },

        get circles() {
          return document.querySelectorAll('.' + Circles.__classes.circle);
        },

        formCircle: function () {
          Circles.wrapper.classList.add('circle-mode');
          Circles.wrapper.classList.remove('line-mode');
          var circles = Circles.circles,
              translateAmount = (Circles.wrapper.offsetWidth / 2) - 1,
              degreeIncrement = 360 / circles.length,
              degrees = parseInt(Circles.__startAngle, 10);

          for (var i = 0; i < circles.length; i++) {
            circles[i].classList.remove(Circles.__classes.linedUp);
            circles[i].classList.remove(Circles.__classes.oneCircle);

            circles[i].style.setProperty('left', '50%');
            circles[i].style.setProperty('-webkit-transform', 'scale(1) rotate(' + (degrees += degreeIncrement) + 'deg) translate(' + translateAmount + 'px) rotate(-' + degrees + 'deg)');
          }
        },

        formLine: function () {
          Circles.wrapper.classList.add('line-mode');
          Circles.wrapper.classList.remove('circle-mode');
          var circles = Circles.circles,
              lineWidth = -Circles.wrapper.offsetWidth,
              centerPoint = Circles.wrapper.offsetWidth / 2,
              leftIncrement = false,
              left = false;

          for (var i = 0; i < circles.length; i++) {
            if (!leftIncrement) {
              leftIncrement = circles[i].offsetWidth + (circles[i].offsetWidth / 8);
              left = ((centerPoint - (leftIncrement / 2) * (circles.length))) - leftIncrement + 10;
            }

            circles[i].classList.add(Circles.__classes.linedUp);

            if (circles.length === 1)
              circles[i].classList.add(Circles.__classes.oneCircle);

            circles[i].style.setProperty('-webkit-transform', 'rotate(0)')
            circles[i].style.setProperty('left', (left += leftIncrement) + 'px')
          }
        },

        setActive: function (category, isViaPopstate) {
        //  circles = Circles.circles;
          var newCategory = document.getElementById(category);
          newCategory.classList.add('circle-active');

          if(!isViaPopstate) {
            history.pushState({}, category, '/category/' + category);
          }

          Circles.loadCategory(category);
          Circles.formLine();
        },

        loadCategory: function (category) {
          // Add .show to ensure the container is displayed whenever there is data.

          if(Circles.__restaurantCache[category]) {
            $('#restaurants-info-container').html(Circles.__restaurantCache[category]).show('fast');
          } else {
            $.get('/category/' + category + '/restaurants').done(function (category, restaurantHTML) {
                Circles.__restaurantCache[category] = restaurantHTML;
                $('#restaurants-info-container').html(restaurantHTML).show('fast');
              }.bind(null, category)).fail(function(error) {
                 console.log('AJAX ERROR', error);
              }
            );
          }
        },

        getActiveCategoryFromURL: function() {
          var splitPath = window.location.pathname.split('/');
          if(Circles.__CATEGORIES.indexOf(splitPath[2]) > -1 ) {
            return splitPath[2];
          } else {
            return null;
          }
        },

        // Self explanitory!?!?
        goHome: function (isViaPopstate) {
          if (!isViaPopstate)
            history.pushState({}, '', '/');

          Circles.formCircle();

          $('#restaurants-info-container').hide('fast'); // Hide the container when there is nothing in it.
        }
      };

      $(function () {
        // Since we have no yet loaded restaurant info, hide the container for it.
        $('#restaurants-info-container').hide();

        // Doing this in a timeout should fix the exploding circle bug.
        setTimeout(initializeCircles, 100);
      });

      $(document)
        .on('click touchend', 'a.category', function() {
          circles = $('.category').removeClass('circle-active');
          circles.removeClass('circle-active');
          var category = this.getAttribute('data-category');
          Circles.setActive(category);
          document.body.offsetWidth;

        })

        // Chain another .on to bind to the go-home link.
        .on('click', '#go-home', function (event) {
          event.preventDefault(); // Prevents the link from actually doing anything so I can control it with JS.

          Circles.goHome();
        });

      window.addEventListener('popstate', function (event) {
        circles = $('.category').removeClass('circle-active');
        circles.removeClass('circle-active');
        var category = Circles.getActiveCategoryFromURL();

        if (category)
          Circles.setActive(category, true);
        else
          Circles.goHome(true); // This should fix the back-to-homepage bug.
      }, true);


      function initializeCircles() {
        // This is called once the circles are moved into position to ensure that the animation duration is set when loaded in line-mode.
        setTimeout(function () {
          document.body.classList.add('page-loaded');
        }, 1000);

        if(!Circles.getActiveCategoryFromURL()) {
          // When loading in circle mode, add page-loaded so that the animation will show.
          document.body.classList.add('page-loaded');

          Circles.formCircle();
        } else {
          Circles.setActive(Circles.getActiveCategoryFromURL());
          Circles.formLine();
        }
      };

      // create: function () {
      //   Circles.destroy();
      //
      //   var circle;
      //
      //   var countInput = document.getElementById('circle-count');
      //
      //   for (var i = 0; i < parseInt(countInput.value); i++) {
      //     circle = document.createElement('div');
      //
      //     circle.className = Circles.__classes.circle;
      //     circle.innerHTML = i + 1;
      //
      //     Circles.wrapper.appendChild(circle);
      //   }
      //
      //   setTimeout(Circles.formCircle, 100);
      // },
      //
      // destroy: function () {
      //   var circles = Circles.circles;
      //
      //   for (var i = circles.length; i--;)
      //     circles[i].parentNode.removeChild(circles[i]);
      // },
