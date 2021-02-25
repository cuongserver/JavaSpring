import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    // matcher: (segments) => {
    //   console.log(segments);
    //   if (segments.length >= 1) {
    //     if (segments[0].path === 'entry') {
    //       return { consumed: segments };
    //     }
    //   }
    //   return null;
    // },
    path: 'entry',
    loadChildren: () =>
      import('@src/app/modules/entry-module/entry.module').then(
        (x) => x.EntryModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
