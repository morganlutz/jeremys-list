
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

        setActive: function (category) {
          //marks circles as active and calls .loadCategory(category)
          //also do history.pushState stuff
          var circles = Circles.circles;
          circles = $('.category').removeClass('circle-active');
          var splitPath = window.location.pathname.split('/');
          var circle = document.getElementById(splitPath[2]);
            if (circle){
              circle.classList.add('circle-active');
            }
          },

        loadCategory: function (category) {
          var category = getActiveCategoryFromURL();
        },

        getActiveCategoryFromURL: function() {
          var splitPath = window.location.pathname.split('/');
          if(CATEGORIES.indexOf(splitPath[2]) > -1 ) {
            return splitPath[2];
          } else {
            return CATEGORIES[0];
          }
        }
      };

       $(initializeCircles);



      $(document).on('click', 'a.category', function() {
        var category = this.getAttribute('data-category');
        history.pushState({}, category, '/category/' + category);
        var isActive = this.classList.contains('circle-active'),
        circles = $('.category').removeClass('circle-active');

        if(Circles.__restaurantCache[category]) {
          $('#restaurants-info-container').html(Circles.__restaurantCache[category]);
        } else {
          $.get('/category/' + category + '/restaurants')
            .done(
              function (category, restaurantHTML) {
                Circles.__restaurantCache[category] = restaurantHTML;
                $('#restaurants-info-container').html(restaurantHTML);
              }.bind(null, category)
            )
            .fail(
              function(error) {
            console.log('AJAX ERROR', error);
          }
        );
      }


        circles.removeClass('circle-active');

        if (isActive) {
          // Circles.formCircle();

          document.body.offsetWidth;
        }	else {
          this.classList.add('circle-active');
          Circles.formLine();
        }
      });

      window.addEventListener('popstate', function (event) {
        Circles.setActiveFromURL();


      }, true);


      function initializeCircles() {
        if(window.location.pathname === "/") {
          Circles.formCircle();
        } else {
          Circles.setActiveFromURL();
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
