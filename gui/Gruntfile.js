module.exports = function (grunt) {
	// load all grunt tasks matching the ['grunt-*', '@*/grunt-*'] patterns
	require('load-grunt-tasks')(grunt);

	// Project configuration.
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),

		copy: {
			devLib: {
				expand: true,
				filter: 'isFile',
				flatten: true,
				cwd: './bower_components/',
				src: [
					'angular/angular.min.js',
					'livereload-js/dist/livereload.js',
					'cryptico/cryptico.min.js'
				],
				dest: 'build/js/lib/'
			},
			devSrc: {
				expand: true,
				cwd: 'src/js/',
				src: '**',
				dest: 'build/js/'
			},
			devThemes: {
				expand: true,
				cwd: 'src/css/',
				src: '**',
				dest: 'build/css/'
			},
			toServer: {
				expand: true,
				cwd: 'build/',
				src: '**',
				dest: '../server/web/'
			}
		},

		jade: {
			dev: {
				options: {
					pretty: true,
					data: {
						debug: true
					}
				},
				files: {
					//"build/decoy.html": 'src/decoy.jade',
					"build/index.html": 'src/index.jade',
					"build/administrador.html": 'src/administrador.jade',
					"build/error503.html": 'src/error503.jade',
					"build/error404.html": 'src/error404.jade',
                                        "build/error500.html": 'src/error500.jade',
					"build/usuario.html": 'src/usuario.jade',
					"build/paneles/panel_admin.html": 'src/theme/Vistas_JADE/admin_main.jade',
					"build/login_general.html": 'src/theme/Vistas_JADE/login_general.jade',
					"build/registro_general.html": 'src/registro_general.jade',

				}
			},
			prod: {
				options: {
					pretty: false,
					data: {
						debug: false
					}
				},
				files: {
					//"build/index.html": 'src/index.jade',//decoy
					"build/index.html": 'src/index.jade',//real app
				}
			}
		},

		stylus: {
			dev: {
				options: {
					compress: false,
					'include css': true
				},
				files: {
					'build/main.css': 'src/css/main.styl',
					//'build/decoy.css': 'src/css/decoy.styl',
				//'path/to/another.css': ['path/to/sources/*.styl', 'path/to/more/*.styl'] // compile and concat into single file
				}
			}
		},

		watch: {
			views: {
				files: ['src/**/*.jade', 'src/**/*.svg'],
				tasks: ['jade:dev']
			},
			css: {
				files: [
					'src/css/*.styl',
					'src/css/*.css'
				],
				tasks: [
					//'stylus:dev'
					'copy:devThemes'
				]
			},
			js: {
				files: 'src/js/**/*.js',
				tasks: ['copy:devSrc'],
			},
			Server: {
				files: 'build/**/*',
				tasks: ['copy:toServer']
			},
			Client: {
				files: 'build/**/*',
				options: {
					livereload: true
				}
			}
		}
	});


	// Default task(s).
	grunt.registerTask(
		'client', [
			'jade:dev',
			'copy:devLib',
			'copy:devSrc',
			'copy:devThemes',
			'watch:views',
			'watch:css',
			'watch:js',
			'watch:Client',
		]
	);
	grunt.registerTask(
		'toServer', [
			'jade:dev',
			'copy',
			'watch'
		]
	);
	grunt.registerTask('default', 'toServer');
};
