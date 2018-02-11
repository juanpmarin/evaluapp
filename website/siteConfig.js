/**
 * Copyright (c) 2017-present, Facebook, Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

const siteConfig = {
    title: 'EvaluApp' /* title for your website */,
    tagline: 'Generación de exámenes fácil',
    url: 'https://facebook.github.io' /* your website url */,
    baseUrl: '/evaluapp/' /* base url for your project */,
    projectName: 'evaluapp',
    headerLinks: [
        { doc: 'doc1', label: 'Docs' },
        { page: 'help', label: 'Help' },
    ],
    /* path to images for header/footer */
    headerIcon: 'img/logo.png',
    footerIcon: 'img/logo.png',
    favicon: 'img/favicon/favicon.ico',
    /* colors for website */
    colors: {
        primaryColor: '#BA0F17',
        secondaryColor: '#32BEA6',
    },
    // This copyright info is used in /core/Footer.js and blog rss/atom feeds.
    copyright:
    'Copyright © ' +
    new Date().getFullYear() +
    ' Your Name or Your Company Name',
    // organizationName: 'deltice', // or set an env variable ORGANIZATION_NAME
    highlight: {
        // Highlight.js theme to use for syntax highlighting in code blocks
        theme: 'default',
    },
    scripts: ['https://buttons.github.io/buttons.js'],
    // You may provide arbitrary config keys to be used as needed by your template.
    repoUrl: 'https://github.com/juanpmarin/evaluapp',
};

module.exports = siteConfig;
