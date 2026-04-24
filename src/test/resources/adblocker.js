// Ad Blocker Injection Script - Loaded externally to avoid Java string escaping issues
// Hides ad containers, blocks ad requests, removes ad scripts

(() => {
  // 1. Block ad networks (prevent ad script and request loading)
  const adNetworks = [
    'doubleclick.net',
    'googlesyndication.com',
    'amazon-adsystem.com',
    'adsystem.amazon.com',
    'googletagmanager.com',
    'googletagservices.com',
    'googleadservices.com'
  ];

  const blockRequests = () => {
    // Override fetch
    if (window.stopAd && window.stopAd.fetch) {
      window.stopAd.fetch = function() { return new Response(''); };
    }
    const originalFetch = window.fetch;
    window.fetch = function(url, ...args) {
      if (url && adNetworks.some(network => url.includes(network))) {
        console.log('[AdBlock] Blocked fetch:', url);
        return Promise.reject(new Error('Ad blocked'));
      }
      return originalFetch.apply(this, [url, ...args]);
    };

    // Override XMLHttpRequest
    const originalXHROpen = XMLHttpRequest.prototype.open;
    XMLHttpRequest.prototype.open = function(method, url) {
      if (url && adNetworks.some(network => url.includes(network))) {
        console.log('[AdBlock] Blocked XHR:', url);
        return;
      }
      return originalXHROpen.apply(this, arguments);
    };
  };

  // 2. Hide ad containers (comprehensive selectors)
  const hideAdSelectors = [
    // Common ad class patterns
    '[class*="ad-"]', '[class*="ads-"]', '[class*="advert"]',
    '[class*="advertisement"]', '[class*="promo"]', '[class*="sponsor"]',
    '[class*="banner"]',
    // Common ad ID patterns
    '[id*="ad-"]', '[id*="ads-"]', '[id*="advert"]', '[id*="advertisement"]',
    '[id*="promo"]', '[id*="sponsor"]',
    // Known ad network containers
    'iframe[src*="doubleclick"]', 'iframe[src*="googleadservices"]',
    'iframe[src*="amazon-adsystem"]', 'iframe[src*="googlesyndication"]',
    'div[id*="google_ads"]', 'div[id*="gpt-ad"]', 'ins.adsbygoogle',
    '[class*="adsbox"]',
    // Popup/overlay ads
    '[class*="modal-ad"]', '[class*="popup-ad"]', '[class*="overlay-ad"]',
    '[class*="lightbox-ad"]',
    // Native ad patterns
    '[data-ad-client]', '[data-ad-slot]', '[data-ad-format]'
  ];

  hideAdSelectors.forEach(selector => {
    document.querySelectorAll(selector).forEach(el => {
      el.style.display = 'none';
      el.style.visibility = 'hidden';
      if (el.remove) { el.remove(); }
    });
  });

  // 3. Remove script tags that load ads
  const adScripts = [
    'doubleclick',
    'googlesyndication',
    'googleadservices',
    'amazon-adsystem',
    'adservice',
    'adsystem'
  ];
  document.querySelectorAll('script').forEach(script => {
    const src = script.src || '';
    if (adScripts.some(ad => src.includes(ad))) {
      script.remove();
      console.log('[AdBlock] Removed script:', src);
    }
  });

  // 4. Block image-based ads
  const adImages = [
    'pagead2.googlesyndication.com',
    'tpc.googlesyndication.com'
  ];
  document.querySelectorAll('img').forEach(img => {
    const src = img.src || '';
    if (adImages.some(ad => src.includes(ad))) {
      img.style.display = 'none';
    }
  });

  // 5. Initialize request blocking
  blockRequests();
})();
