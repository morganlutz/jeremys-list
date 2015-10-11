
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
        //  circles = Circles.circles;
          var newCategory = document.getElementById(category);
          newCategory.classList.add('circle-active');
          history.pushState({}, category, '/category/' + category);
          Circles.loadCategory(category);
          Circles.formLine();
        },

        loadCategory: function (category) {
          if(Circles.__restaurantCache[category]) {
            $('#restaurants-info-container').html(Circles.__restaurantCache[category]);
          } else {
            $.get('/category/' + category + '/restaurants').done(function (category, restaurantHTML) {
                Circles.__restaurantCache[category] = restaurantHTML;
                $('#restaurants-info-container').html(restaurantHTML);
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
        }
      };

       $(initializeCircles);

      $(document).on('click touchend', 'a.category', function() {
        circles = $('.category').removeClass('circle-active');
        circles.removeClass('circle-active');
        var category = this.getAttribute('data-category');
        Circles.setActive(category);
        Circles.loadCategory(category);
        document.body.offsetWidth;

      });

      window.addEventListener('popstate', function (event) {
        circles = $('.category').removeClass('circle-active');
        circles.removeClass('circle-active');
        var category = Circles.getActiveCategoryFromURL();
        Circles.setActive(category);
        Circles.loadCategory(category);
      }, true);


      function initializeCircles() {
        if(!Circles.getActiveCategoryFromURL()) {
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
