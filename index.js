const report = require('multiple-cucumber-html-reporter');

report.generate({
	jsonDir: './Report/Test/',
	reportPath: './Report/Test/',
	openReportInBrowser: false,
	displayDuration:true,
	reportName: "Yape Automated",
	pageTitle: "Yape Automated",

	metadata:{
        browser: {
            name: 'android',
            version: '11'
        },
        device: 'Local test machine',
        platform: {
            name: 'Windows',
            version: '11'
        }
    },
    customData: {
        title: 'Run info',
        data: [
            {label: 'Project', value: 'YAPE'},
            {label: 'Release', value: '1'},
            {label: 'Cycle', value: '1'},
        ]
    }
});